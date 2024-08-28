package com.lcaohoanq.Spring_Snake_Game.dto.request;


import com.lcaohoanq.Spring_Snake_Game.entity.Role;
import com.lcaohoanq.Spring_Snake_Game.entity.Status;
import com.lcaohoanq.Spring_Snake_Game.enums.UserGenderEnum;
import java.time.LocalDateTime;
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
    private Role role;
    private Status status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String avatar_url;
    private int google_account_id;
    private int facebook_account_id;
}
