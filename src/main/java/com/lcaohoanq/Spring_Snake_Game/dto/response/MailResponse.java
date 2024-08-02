package com.lcaohoanq.Spring_Snake_Game.dto.response;

import com.lcaohoanq.Spring_Snake_Game.dto.AbstractResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MailResponse extends AbstractResponse {

    protected String otp;

    public MailResponse(String message, String status) {
        super(message, status);
    }

    public MailResponse(String message, String status, String otp) {
        super(message);
        this.otp = otp;
    }

}
