package com.lcaohoanq.Spring_Snake_Game.dto.request;


import com.lcaohoanq.Spring_Snake_Game.enums.UserGenderEnum;
import com.lcaohoanq.Spring_Snake_Game.enums.UserRoleEnum;
import com.lcaohoanq.Spring_Snake_Game.enums.UserStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {
    private Long id;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private String birthday;
    private UserGenderEnum gender;
    private UserRoleEnum role;
    private UserStatusEnum status;
    private String created_at;
    private String updated_at;
    private byte[] avatar_url;
}
