package com.lcaohoanq.Spring_Snake_Game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JwtResponse implements ApiResponse {
    private String accessToken;
    private String refreshToken;
}
