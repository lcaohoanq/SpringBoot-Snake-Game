package com.lcaohoanq.Spring_Snake_Game.exception;

public class UserNotFoundException extends RuntimeException {

    private Long id;

    public UserNotFoundException(Long id) {
        super("Could not find user " + id);
    }

}
