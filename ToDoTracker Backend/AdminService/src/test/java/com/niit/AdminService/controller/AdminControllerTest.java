package com.niit.AdminService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.AdminService.domain.Admin;
import com.niit.AdminService.domain.Task;
import com.niit.AdminService.service.AdminServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {
    @Mock
    private AdminServiceImpl adminServiceImpl;

    @InjectMocks
    private AdminController AdminController;

    private Admin admin1;
    private List<Task> list1;
    private Task task1,task2;
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    public void setUp() {
        task1=new Task(1,"DemoTest");
        task2=new Task(2,"Test");
        list1 = new ArrayList<>(Arrays.asList(task1, task2));
        admin1 = new Admin("anuj@gmail.com", "anuj123", list1);
        mockMvc = MockMvcBuilders.standaloneSetup(AdminController).build();
    }

    @AfterEach
    public void tearDown() {
        admin1 = null;
        list1 = null;
    }
    @Test
    public void userRegistryPositiveTest() throws Exception {
        when(adminServiceImpl.saveAdmin(any())).thenReturn(admin1);
        mockMvc.perform(post("/api/v3/adminregister")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(admin1)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(adminServiceImpl, times(1)).saveAdmin(any());
    }

    @Test
    public void saveTaskPositiveTest() throws Exception {
        when(adminServiceImpl.addToTask(anyString(), any())).thenReturn(admin1);
        task1.setTaskId(1);
        mockMvc.perform(put("/api/v3/addtotask/anuj@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(admin1)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(adminServiceImpl, times(1)).addToTask(anyString(), any());
    }

    @Test
    public void deleteTaskPositiveTest() throws Exception {

        when(adminServiceImpl.deleteFromTask(anyString(), anyLong())).thenReturn(true);
        mockMvc.perform(delete("/api/v3/deletefromtask/anuj@gmail.com/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(admin1)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(adminServiceImpl, times(1)).deleteFromTask(anyString(), anyLong());
    }
    private static String jsonToString(final Object obj) throws JsonProcessingException {
        String result = null;
        try {
            ObjectMapper mapper = new ObjectMapper(); // provides functionality for reading and writing JSON, either to and from POJO
            String jsonContent = mapper.writeValueAsString(obj);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            result = "error while conversion";
        }
        return result;
    }
}
