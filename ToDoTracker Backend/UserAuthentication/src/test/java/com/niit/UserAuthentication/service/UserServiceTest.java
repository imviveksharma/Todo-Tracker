package com.niit.UserAuthentication.service;

import com.niit.UserAuthentication.domain.User;

import com.niit.UserAuthentication.exception.UserNotFoundException;
import com.niit.UserAuthentication.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    private User user1;


    @BeforeEach
    public void setUp(){
        user1=new User("vivek121004@gmail.com","123456");
    }
    @AfterEach
    public void tearDown(){
        user1=null;

    }

    @Test
    public void findByEmailIdAndPasswordPositiveTestCase() throws UserNotFoundException {
        when(userRepository.findByEmailAndPassword(user1.getEmail(),user1.getPassword())).thenReturn(user1);
        assertEquals(user1, userServiceImpl.findByEmailAndPassword(user1.getEmail(),user1.getPassword()));
        verify(userRepository,times(1)).findByEmailAndPassword(user1.getEmail(),user1.getPassword());
    }

    @Test
    public void findByEmailIdAndPasswordNegativeTestCase()
    {
        when(userRepository.findByEmailAndPassword(user1.getEmail(),user1.getPassword())).thenReturn(null);
        assertThrows(UserNotFoundException.class,()->userServiceImpl.findByEmailAndPassword(user1.getEmail(),user1.getPassword()));
        verify(userRepository,times(1)).findByEmailAndPassword(user1.getEmail(),user1.getPassword());
    }
}
