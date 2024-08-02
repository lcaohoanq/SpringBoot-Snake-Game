package com.lcaohoanq.Spring_Snake_Game.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdatePasswordRequest {

    private Long id;
    private String email;
    private String phone;
    private String newPassword;

}
