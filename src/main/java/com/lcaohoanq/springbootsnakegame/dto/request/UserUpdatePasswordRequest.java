package com.lcaohoanq.springbootsnakegame.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserUpdatePasswordRequest {

    private String identifier;
    private String newPassword;

}
