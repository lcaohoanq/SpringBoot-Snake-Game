package com.lcaohoanq.springbootsnakegame.dto.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginRequest {

    @NotEmpty(message = "Email or Phone is required")
    private String email_phone;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 20)
    private String password;
}
