package com.lcaohoanq.Spring_Snake_Game.dto;


import lombok.Getter;

@Getter
public class UserLoginRequest {

    private String email_phone;
    private String password;

    public UserLoginRequest(String email_phone, String password) {
        this.email_phone = email_phone;
        this.password = password;
    }

}
