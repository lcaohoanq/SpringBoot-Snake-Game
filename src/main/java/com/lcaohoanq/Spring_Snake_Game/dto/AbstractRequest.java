package com.lcaohoanq.Spring_Snake_Game.dto;

import com.lcaohoanq.Spring_Snake_Game.entity.User;

public abstract class AbstractRequest extends User {
    public AbstractRequest(String email_phone, String password) {
        super(email_phone, password);
    }
}
