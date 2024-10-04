package com.lcaohoanq.springbootsnakegame.controller;

import com.lcaohoanq.springbootsnakegame.model.response.PhoneResponse;
import com.lcaohoanq.springbootsnakegame.model.User;
import com.lcaohoanq.springbootsnakegame.enums.UserStatusEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhoneController {

    @Autowired
    private HttpServletRequest request;

    public ResponseEntity<PhoneResponse> sendPhoneOtp(@RequestParam String toPhone) {

        User user = (User) request.getAttribute("validatedPhone");

        if(user.getStatus().getId() == UserStatusEnum.VERIFIED.getStatus()){
            return new ResponseEntity<>(new PhoneResponse("Phone number already verified", "error"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new PhoneResponse("OTP sent successfully", "ok"), HttpStatus.OK);
    }

}