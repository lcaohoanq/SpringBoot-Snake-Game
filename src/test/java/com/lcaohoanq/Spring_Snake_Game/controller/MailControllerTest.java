package com.lcaohoanq.Spring_Snake_Game.controller;

import com.lcaohoanq.Spring_Snake_Game.dto.response.MailResponse;
import com.lcaohoanq.Spring_Snake_Game.entity.Status;
import com.lcaohoanq.Spring_Snake_Game.entity.User;
import com.lcaohoanq.Spring_Snake_Game.enums.UserStatusEnum;
import com.lcaohoanq.Spring_Snake_Game.service.MailSenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Disabled
public class MailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MailSenderService mailSenderService;

    private User mockUser;

    @BeforeEach
    public void setUp() {
        mockUser = new User();
        mockUser.setFirstName("hoang");
        mockUser.setStatus(new Status(0, UserStatusEnum.UNVERIFIED));
    }

    private RequestPostProcessor userRequestPostProcessor() {
        return request -> {
            request.setAttribute("validatedEmail", mockUser);
            return request;
        };
    }

    @Test
    void testSendForgotPassword() throws Exception {
        mockMvc.perform(get("/mail/forgot-password")
                .param("toEmail", "hoangdz1604@gmail.com"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("Mail sent successfully"))
            .andExpect(jsonPath("$.otp").isNotEmpty());
    }

    @Test
    void testSendBlockAccount() throws Exception {
        mockMvc.perform(get("/mail/block")
                .param("toEmail", "hoangdz1604@gmail.com"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("Mail sent successfully"));
    }

    @Test
    void testSendOtp() throws Exception {
        mockMvc.perform(get("/otp/send")
                .param("toEmail", "hoangdz1604@gmail.com")
                .param("type", "email"))  // Make sure this matches the expected parameters
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("Mail sent successfully"))
            .andExpect(jsonPath("$.otp").isNotEmpty());
    }
}
