package com.lcaohoanq.Spring_Snake_Game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
