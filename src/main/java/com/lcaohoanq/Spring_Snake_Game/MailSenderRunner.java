package com.lcaohoanq.Spring_Snake_Game;

import com.lcaohoanq.Spring_Snake_Game.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MailSenderRunner implements CommandLineRunner {

    @Autowired
    private MailSenderService mailSenderService;

    @Override
    public void run(String... args) throws Exception {
        mailSenderService.sendNewMail("hoangdz1604@gmail.com", "Test", "Test");
    }
}
