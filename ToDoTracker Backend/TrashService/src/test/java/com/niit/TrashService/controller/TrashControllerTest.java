package com.niit.TrashService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.TrashService.exception.TaskAlreadyExistsException;
import com.niit.TrashService.exception.TrashAlreadyExistsException;
import com.niit.TrashService.model.TaskList;
import com.niit.TrashService.model.User;
import com.niit.TrashService.service.TrashServiceImpl;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TrashControllerTest {
    @Mock
    private TrashServiceImpl trashServiceImpl;

    @InjectMocks
    private TrashController trashController;

    private User user1;
    private List<TaskList> list1;
    private TaskList task1,task2;
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    public void setUp() {
        task1=new TaskList(1,"DemoTest","Official","Testing","HIGH","22-10-2022");
        task2=new TaskList(1,"Test","NON-Official","Testing","LOW","20-10-2022");
        list1 = new ArrayList<>(Arrays.asList(task1, task2));
        user1 = new User("anuj@gmail.com", "anuj123", list1);
        mockMvc = MockMvcBuilders.standaloneSetup(trashController).build();
    }

    @AfterEach
    public void tearDown() {
        user1 = null;
        list1 = null;
    }
    @Test
    public void userRegistryPositiveTest() throws Exception {
        when(trashServiceImpl.saveUser(any())).thenReturn(user1);
        mockMvc.perform(post("/api/v4/registertrash")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(user1)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(trashServiceImpl, times(1)).saveUser(any());
    }

    @Test
    public void saveTaskPositiveTest() throws Exception {

        when(trashServiceImpl.saveToTrash(anyString(), any())).thenReturn(user1);
        task1.setTaskId(1);
        mockMvc.perform(put("/api/v4/trash/anuj@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(user1)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(trashServiceImpl, times(1)).saveToTrash(anyString(), any());
    }

    @Test
    public void deleteTaskPositiveTest() throws Exception {

        when(trashServiceImpl.deleteFromTask(anyString(), anyLong())).thenReturn(true);
        mockMvc.perform(delete("/api/v4/deletefromtrash/anuj@gmail.com/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(user1)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(trashServiceImpl, times(1)).deleteFromTask(anyString(), anyLong());
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
