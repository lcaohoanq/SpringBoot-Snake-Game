package com.lcaohoanq.Spring_Snake_Game.dto.response;

import com.lcaohoanq.Spring_Snake_Game.dto.base.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailResponse extends Response {

    private String message;

}
