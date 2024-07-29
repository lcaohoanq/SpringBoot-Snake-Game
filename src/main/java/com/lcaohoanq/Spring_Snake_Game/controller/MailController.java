package com.lcaohoanq.Spring_Snake_Game.controller;

import com.lcaohoanq.Spring_Snake_Game.dto.MailRequest;
import com.lcaohoanq.Spring_Snake_Game.dto.MailResponse;
import com.lcaohoanq.Spring_Snake_Game.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

@RestController
public class MailController {

    @Autowired
    private MailSenderService mailSenderService;

    @PostMapping("/send-mail")
    ResponseEntity<MailResponse> sendMail(@RequestBody MailRequest mailRequest) {
        try {
            Context context = new Context();
            mailSenderService.sendNewMail(mailRequest.getTo(), mailRequest.getSubject(), mailRequest.getBody(), context);
            MailResponse response = new MailResponse("Mail sent successfully", true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            MailResponse response = new MailResponse("Failed to send mail: " + e.getMessage(), false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
