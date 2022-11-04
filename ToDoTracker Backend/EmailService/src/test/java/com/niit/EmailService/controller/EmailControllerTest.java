package com.niit.EmailService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.EmailService.model.Email;
import com.niit.EmailService.service.EmailService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EmailControllerTest {
    @Mock
    private EmailService emailService;

    @InjectMocks
    private EmailController emailController;

    private Email email;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        email = new Email("vivek121003@gmail.com","Demo","This mail is for demo purpose");
        mockMvc = MockMvcBuilders.standaloneSetup(emailController).build();
    }

    @AfterEach
    public void tearDown() {
        email=null;
    }
    @Test
    public void userSendMailPositiveTest() throws Exception {
        when(emailService.sendEmail("vivek121003@gmail.com","Demo","This mail is for demo purpose"))
                .thenReturn(true);
        mockMvc.perform(post("/api/v5/sendemail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(email)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(emailService, times(1)).sendEmail("vivek121003@gmail.com","Demo","This mail is for demo purpose");
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
