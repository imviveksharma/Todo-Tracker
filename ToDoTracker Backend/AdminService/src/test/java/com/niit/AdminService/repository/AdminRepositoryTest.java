package com.niit.AdminService.repository;

import com.niit.AdminService.domain.Admin;
import com.niit.AdminService.domain.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class AdminRepositoryTest {
    @Autowired
    private AdminRepository adminRepository;
    private Admin admin;
    private Task task;
    @BeforeEach
    public void setUp() {
        admin=new Admin();
        task = new Task();

        admin.setEmail("vivek121003@gmail.com");
        admin.setPassword("vivek123");

        task.setTaskId(1);
        task.setTaskTitle("testing");
    }

    @AfterEach
    public void tearDown() {
        admin = null;
        task = null;
        adminRepository.deleteAll();
    }
    @Test
    public void saveUser(){
        adminRepository.save(admin);
        Admin admin1=adminRepository.findById(admin.getEmail()).get();
        assertNotNull(admin1);
        assertEquals(admin.getEmail(),admin1.getEmail());
    }
}
