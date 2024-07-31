package com.lcaohoanq.Spring_Snake_Game.controller;

import com.lcaohoanq.Spring_Snake_Game.constant.EmailSubject;
import com.lcaohoanq.Spring_Snake_Game.dto.MailRequest;
import com.lcaohoanq.Spring_Snake_Game.dto.MailResponse;
import com.lcaohoanq.Spring_Snake_Game.entity.User;
import com.lcaohoanq.Spring_Snake_Game.enums.EmailBlockReasonEnum;
import com.lcaohoanq.Spring_Snake_Game.enums.EmailCategoriesEnum;
import com.lcaohoanq.Spring_Snake_Game.exception.UserNotFoundException;
import com.lcaohoanq.Spring_Snake_Game.repository.UserRepository;
import com.lcaohoanq.Spring_Snake_Game.service.MailSenderService;
import com.lcaohoanq.Spring_Snake_Game.util.OTPUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

@RestController
public class MailController {

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/mail/send-otp")
    ResponseEntity<MailResponse> sendOtp(@RequestParam String toEmail) {
        User user = (User) request.getAttribute("validatedUser");
        String name = user.getFirstName();
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("otp", OTPUtils.generateOTP());
        mailSenderService.sendNewMail(toEmail, EmailSubject.subjectGreeting(name),
            EmailCategoriesEnum.OTP.getType(),
            context);
        MailResponse response = new MailResponse("Mail sent successfully", "ok");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/mail/block")
    ResponseEntity<MailResponse> sendBlockAccount(@RequestParam String toEmail) {
        User user = (User) request.getAttribute("validatedUser");
        Context context = new Context();
        context.setVariable("reason", EmailBlockReasonEnum.ABUSE.getReason());
        mailSenderService.sendNewMail(toEmail, EmailSubject.subjectBlockEmail(user.getFirstName()),
            EmailCategoriesEnum.BLOCK_ACCOUNT.getType(), context);
        MailResponse response = new MailResponse("Mail sent successfully", "ok");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/mail/forgot-password")
    ResponseEntity<MailResponse> sendForgotPassword(@RequestParam String toEmail) {
        User user = (User) request.getAttribute("validatedUser");
        String name = user.getFirstName();
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("otp", OTPUtils.generateOTP());
        mailSenderService.sendNewMail(toEmail, EmailSubject.subjectGreeting(name), EmailCategoriesEnum.FORGOT_PASSWORD.getType(), context);
        MailResponse response = new MailResponse("Mail sent successfully", "ok");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
