package com.niit.ToDoService.repository;

import com.niit.ToDoService.model.TempTaskList;
import com.niit.ToDoService.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private User user;
    private TempTaskList task;
    @BeforeEach
    public void setUp() {
        user = new User();
        task = new TempTaskList();

        user.setEmail("vivek121003@gmail.com");
        user.setName("Vivek");
        user.setPassword("vivek123");
        user.setPhoneNo(955575841);

        task.setTaskId(1);
        task.setTaskTitle("testing");
        task.setCategory("Official");
        task.setDescription("For Testing");
        task.setPriority("High");
        task.setDate("20-10-2022");
    }

    @AfterEach
    public void tearDown() {
        user = null;
        task = null;
        userRepository.deleteAll();

    }

    @Test
    public void saveUser(){
        userRepository.save(user);
        User user1=userRepository.findById(user.getEmail()).get();
        assertNotNull(user1);
        assertEquals(user.getEmail(),user1.getEmail());
    }

    @Test
    public void deleteUser(){
        userRepository.insert(user);
        User user1=userRepository.findById(user.getEmail()).get();
        userRepository.delete(user1);
        assertEquals(Optional.empty(),userRepository.findById(user.getEmail()));
    }

    @Test
    public  void getAllUsers(){

        userRepository.insert(user);
        List<User> list=userRepository.findAll();
        assertEquals(1,list.size());
        assertEquals("vivek121003@gmail.com",list.get(0).getEmail());
    }

    @Test
    public void updateUser(){
        userRepository.insert(user);
        user.setName("Vivek");
        assertEquals("Vivek",user.getName());
    }
}
