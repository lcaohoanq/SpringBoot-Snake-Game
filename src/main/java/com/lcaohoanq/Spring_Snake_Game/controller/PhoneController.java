package com.lcaohoanq.Spring_Snake_Game.controller;

import com.lcaohoanq.Spring_Snake_Game.dto.PhoneResponse;
import com.lcaohoanq.Spring_Snake_Game.entity.User;
import com.lcaohoanq.Spring_Snake_Game.enums.UserStatusEnum;
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

        if(user.getStatus() == UserStatusEnum.VERIFIED){
            return new ResponseEntity<>(new PhoneResponse("Phone number already verified", "error"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new PhoneResponse("OTP sent successfully", "ok"), HttpStatus.OK);
    }

}
