package com.niit.TrashService.repository;

import com.niit.TrashService.model.TaskList;
import com.niit.TrashService.model.User;
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
public class TrashRepositoryTest {
    @Autowired
    private TrashRepository trashRepository;
    private User user;
    private TaskList task;
    @BeforeEach
    public void setUp() {
        user = new User();
        task = new TaskList();

        user.setEmail("vivek121003@gmail.com");
        user.setPassword("vivek123");

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
        trashRepository.deleteAll();
    }
    @Test
    public void saveUser(){
        trashRepository.save(user);
        User user1=trashRepository.findById(user.getEmail()).get();
        assertNotNull(user1);
        assertEquals(user.getEmail(),user1.getEmail());
    }

    @Test
    public void deleteUser(){
        trashRepository.insert(user);
        User user1=trashRepository.findById(user.getEmail()).get();
        trashRepository.delete(user1);
        assertEquals(Optional.empty(),trashRepository.findById(user.getEmail()));
    }

    @Test
    public  void getAllUsers(){

        trashRepository.insert(user);
        List<User> list=trashRepository.findAll();
        assertEquals(1,list.size());
        assertEquals("vivek121003@gmail.com",list.get(0).getEmail());
    }



}
