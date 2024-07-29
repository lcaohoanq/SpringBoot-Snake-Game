package com.lcaohoanq.Spring_Snake_Game.listener;

import com.lcaohoanq.Spring_Snake_Game.constant.EmailSubject;
import com.lcaohoanq.Spring_Snake_Game.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

@Component
public class MailSenderRunner implements CommandLineRunner {

    @Autowired
    private MailSenderService mailSenderService;

    @Override
    public void run(String... args) throws Exception {
        //send this mail to EmployeeRole
        mailSenderService.sendNewMail("hoangdz1604@gmail.com", EmailSubject.subjectRunningApp(), "appIsRunning", new Context());
    }
}
