package com.lcaohoanq.Spring_Snake_Game.dto.base;

import com.lcaohoanq.Spring_Snake_Game.entity.User;

public class Request extends User {
    public Request(String email_phone, String password) {
        super(email_phone, password);
    }
}
