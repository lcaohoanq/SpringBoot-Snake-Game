package com.lcaohoanq.Spring_Snake_Game.dto.response;

import com.lcaohoanq.Spring_Snake_Game.dto.base.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JwtResponse extends Response {

    public JwtResponse(String accessToken, String refreshToken) {
        super(accessToken, refreshToken);
    }

}
