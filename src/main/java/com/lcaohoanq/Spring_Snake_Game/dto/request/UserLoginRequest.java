package com.lcaohoanq.Spring_Snake_Game.dto.request;


import com.lcaohoanq.Spring_Snake_Game.dto.AbstractRequest;
import lombok.Getter;

@Getter
public class UserLoginRequest extends AbstractRequest {

    private String email_phone;

    public UserLoginRequest(String email_phone, String password) {
        super(email_phone, password);
    }

}
