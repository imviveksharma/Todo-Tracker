package com.niit.ToDoService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.ToDoService.exception.UserAlreadyExistsException;
import com.niit.ToDoService.exception.UserNotFoundException;
import com.niit.ToDoService.model.TempTaskList;
import com.niit.ToDoService.model.User;
import com.niit.ToDoService.service.UserServiceImpl;
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
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserServiceImpl userServiceImpl;

    @InjectMocks
    private UserController userController;

    private User user1;
    private List<TempTaskList> list1;
    private TempTaskList task1,task2;
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    public void setUp() {
        task1=new TempTaskList(1,"DemoTest","Official","Testing","HIGH","22-10-2022");
        task2=new TempTaskList(1,"Test","NON-Official","Testing","LOW","20-10-2022");
        list1 = new ArrayList<>(Arrays.asList(task1, task2));
        user1 = new User("anuj", "anuj@gmail.com", "anuj123", 950664514, list1, null);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @AfterEach
    public void tearDown() {
        user1 = null;
        list1 = null;
    }
    @Test
    public void userRegistryPositiveTest() throws Exception {
        when(userServiceImpl.saveUser(any())).thenReturn(user1);
        mockMvc.perform(post("/api/v2/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(user1)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(userServiceImpl, times(1)).saveUser(any());
    }

    @Test
    public void userRegistryNegativeTest() throws Exception {
        when(userServiceImpl.saveUser(any())).thenThrow(UserAlreadyExistsException.class);
        mockMvc.perform(post("/api/v2/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(user1)))
                .andExpect(status().isConflict())
                .andDo(MockMvcResultHandlers.print());
        verify(userServiceImpl, times(1)).saveUser(any());
    }
    @Test
    public void saveTaskPositiveTest() throws Exception {

        when(userServiceImpl.addToTask(anyString(), any())).thenReturn(user1);
        task1.setTaskId(1);
        mockMvc.perform(put("/api/v2/addtotask/anuj@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(user1)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(userServiceImpl, times(1)).addToTask(anyString(), any());
    }

    @Test
    public void deleteTaskPositiveTest() throws Exception {

        when(userServiceImpl.deleteFromTask(anyString(), anyLong())).thenReturn(true);
        mockMvc.perform(delete("/api/v2/deletefromtask/anuj@gmail.com/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(user1)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(userServiceImpl, times(1)).deleteFromTask(anyString(), anyLong());
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
