package com.lcaohoanq.Spring_Snake_Game.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JwtResponse extends AbstractResponse {

    public JwtResponse(String accessToken, String refreshToken) {
        super(accessToken, refreshToken);
    }

}
