package com.niit.UserAuthentication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.UserAuthentication.domain.User;
import com.niit.UserAuthentication.service.UserService;
import org.assertj.core.util.Arrays;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private UserService userService;
    private User user1,user2;

    List<User> userList;
    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp(){
     user1=new User("vivek121003@gmail.com","123456");
     user2=new User("pawan121003@gmail.com","1321913");
     mockMvc= MockMvcBuilders.standaloneSetup(userController).build();
    }
    @AfterEach
    public void tearDown(){
        user1=null;
        user2=null;
    }
    @Test
    public void givenUsertoSaveReturnSaveSuccess() throws Exception {
        when(userService.saveUser(any())).thenReturn(user1);
        mockMvc.perform(post("/api/v1/register").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user1))).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        verify(userService, times(1)).saveUser(any());
    }

    private static String jsonToString(final Object ob) throws JsonProcessingException {
        String result;
        try{
            ObjectMapper mapper=new ObjectMapper();
            String jsonContent=mapper.writeValueAsString(ob);
            System.out.println("Json content created:"+jsonContent);
            result=jsonContent;
        }catch(JsonProcessingException e){
            result="Json processing error";
        }
        return result;
    }
}
