package com.niit.ToDoService.service;

import com.niit.ToDoService.exception.TaskNotFoundException;
import com.niit.ToDoService.exception.UserAlreadyExistsException;
import com.niit.ToDoService.exception.UserNotFoundException;
import com.niit.ToDoService.model.TempTaskList;
import com.niit.ToDoService.model.User;
import com.niit.ToDoService.proxy.UserAuthProxy;
import com.niit.ToDoService.proxy.UserTrashProxy;
import com.niit.ToDoService.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private User user1;
    private List<TempTaskList> list1;
    private TempTaskList task1, task2;

    @Mock
    private UserAuthProxy userAuthProxy;
    @Mock
    private UserTrashProxy userTrashProxy;

    @BeforeEach
    public void setUp() {
        task1=new TempTaskList(1,"DemoTest","Official","Testing","HIGH","22-10-2022");
        task2=new TempTaskList(1,"Test","NON-Official","Testing","LOW","20-10-2022");
        list1 = new ArrayList<>(Arrays.asList(task1, task2));
        user1 = new User("anuj", "anuj@gmail.com", "anuj123", 950664514, null, null);
    }

    @AfterEach
    public void tearDown() {
        user1 = null;
        list1 = null;
    }

    @Test
    public void registerUserPositiveTestCase() throws UserAlreadyExistsException {
        when(userRepository.findById(user1.getEmail())).thenReturn(Optional.ofNullable(null));
        when(userRepository.save(user1)).thenReturn(user1);
        assertEquals(user1, userServiceImpl.saveUser(user1));
        verify(userRepository, times(1)).findById(user1.getEmail());
        verify(userRepository, times(1)).save(user1);
    }

    @Test
    public void registerUserNegativeTestCase() {
        when(userRepository.findById(user1.getEmail())).thenReturn(Optional.ofNullable(user1));
        assertThrows(UserAlreadyExistsException.class, () -> userServiceImpl.saveUser(user1));
        verify(userRepository, times(1)).findById(user1.getEmail());
        verify(userRepository, times(0)).save(user1);
    }

    @Test
    public void saveTaskPositiveTestCase() throws UserNotFoundException {
        when(userRepository.findById(user1.getEmail())).thenReturn(Optional.ofNullable(user1));
        when(userRepository.save(user1)).thenReturn(user1);
        assertEquals(user1, userServiceImpl.addToTask("anuj@gmail.com", task1));
        verify(userRepository, times(1)).findById(user1.getEmail());
        verify(userRepository, times(1)).save(user1);
    }

    @Test
    public void getAllTasksPositiveTestCase() throws UserNotFoundException {
        user1.setList(list1);
        when(userRepository.findById(user1.getEmail())).thenReturn(Optional.ofNullable(user1));
        assertEquals(list1, userServiceImpl.getFromTask("anuj@gmail.com"));
        verify(userRepository, times(1)).findById(user1.getEmail());
    }

}
