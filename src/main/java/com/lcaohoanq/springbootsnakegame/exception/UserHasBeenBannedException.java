package com.lcaohoanq.springbootsnakegame.exception;

public class UserHasBeenBannedException extends RuntimeException {

    private Long id;
    private String email_phone;

    public UserHasBeenBannedException(String email_phone) {
        super("Account " + email_phone + " has been banned");
        this.email_phone = email_phone;
    }

}
