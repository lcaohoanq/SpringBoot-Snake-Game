package com.lcaohoanq.Spring_Snake_Game.dto.response;

import com.lcaohoanq.Spring_Snake_Game.dto.base.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse extends Response {

    public UserResponse(String message, String status) {
        super(message, status);
    }

    public UserResponse(String message) {
        super(message);
    }

}
