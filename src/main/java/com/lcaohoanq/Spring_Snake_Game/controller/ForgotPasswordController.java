package com.lcaohoanq.Spring_Snake_Game.controller;

import com.lcaohoanq.Spring_Snake_Game.constant.EmailSubject;
import com.lcaohoanq.Spring_Snake_Game.model.response.ForgotPasswordResponse;
import com.lcaohoanq.Spring_Snake_Game.model.User;
import com.lcaohoanq.Spring_Snake_Game.enums.EmailCategoriesEnum;
import com.lcaohoanq.Spring_Snake_Game.repository.UserRepository;
import com.lcaohoanq.Spring_Snake_Game.service.MailSenderService;
import com.lcaohoanq.Spring_Snake_Game.util.OTPUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

@RequestMapping(path = "${v1API}/forgotPassword")
@RestController
public class ForgotPasswordController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MailSenderService mailSenderService;

    @PutMapping("")
    public ResponseEntity<ForgotPasswordResponse> forgotPassword(@RequestParam @Validated String email_phone) {
        User user = (User) request.getAttribute("validatedAccount");

        String name = user.getFirstName();
        Context context = new Context();
        String otp = OTPUtils.generateOTP();
        context.setVariable("name", name);
        context.setVariable("otp", otp);

        if(user.getEmail() != null){
            mailSenderService.sendNewMail(user.getEmail(), EmailSubject.subjectGreeting(name),
                EmailCategoriesEnum.FORGOT_PASSWORD.getType(),
                context);
        } else {
            mailSenderService.sendNewMail(user.getPhone(), EmailSubject.subjectGreeting(name),
                EmailCategoriesEnum.FORGOT_PASSWORD.getType(),
                context);
        }

        ForgotPasswordResponse response = new ForgotPasswordResponse("Forgot password sent successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
