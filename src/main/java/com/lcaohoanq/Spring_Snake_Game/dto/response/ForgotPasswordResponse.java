package com.lcaohoanq.Spring_Snake_Game.dto.response;

public class ForgotPasswordResponse extends MailResponse{

    public ForgotPasswordResponse(String message, String status, String otp) {
        super(message, status, otp);
    }

    public ForgotPasswordResponse(String message, String status) {
        super(message, status);
    }

}
