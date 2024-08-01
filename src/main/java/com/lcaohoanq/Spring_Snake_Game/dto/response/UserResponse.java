package com.lcaohoanq.Spring_Snake_Game.dto.response;

import com.lcaohoanq.Spring_Snake_Game.dto.AbstractResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse extends AbstractResponse {

    public UserResponse(String message, String status) {
        super(message, status);
    }

    public UserResponse(String message) {
        super(message);
    }

}
