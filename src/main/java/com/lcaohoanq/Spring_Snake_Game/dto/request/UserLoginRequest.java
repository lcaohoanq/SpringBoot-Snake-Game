package com.lcaohoanq.Spring_Snake_Game.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginRequest {
    private String email_phone;
    private String password;
}
