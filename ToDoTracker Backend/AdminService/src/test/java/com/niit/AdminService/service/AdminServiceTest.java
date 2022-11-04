package com.niit.AdminService.service;

import com.niit.AdminService.domain.Admin;
import com.niit.AdminService.exception.AdminNotFoundException;
import com.niit.AdminService.repository.AdminRepository;
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
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
    @InjectMocks
    private AdminServiceImpl adminServiceImpl;

    @Mock
    private AdminRepository adminRepository;

    private Admin admin1;


    @BeforeEach
    public void setUp(){
        admin1=new Admin();
        admin1.setEmail("admin@gmail.com");
        admin1.setPassword("123456");
    }
    @AfterEach
    public void tearDown(){
        admin1=null;
    }
    @Test
    public void findByEmailIdAndPasswordPositiveTestCase() throws AdminNotFoundException {
        when(adminRepository.findByEmailAndPassword(admin1.getEmail(),admin1.getPassword())).thenReturn(admin1);
        assertEquals(admin1, adminServiceImpl.findByEmailAndPassword(admin1.getEmail(),admin1.getPassword()));
        verify(adminRepository,times(1)).findByEmailAndPassword(admin1.getEmail(),admin1.getPassword());
    }

    @Test
    public void findByEmailIdAndPasswordNegativeTestCase()
    {
        when(adminRepository.findByEmailAndPassword(admin1.getEmail(),admin1.getPassword())).thenReturn(null);
        assertThrows(AdminNotFoundException.class,()->adminServiceImpl.findByEmailAndPassword(admin1.getEmail(),admin1.getPassword()));
        verify(adminRepository,times(1)).findByEmailAndPassword(admin1.getEmail(),admin1.getPassword());
    }
}
