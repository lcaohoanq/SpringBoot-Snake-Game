package com.lcaohoanq.Spring_Snake_Game.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class MethodArgumentNotValidException extends RuntimeException {

    private final BindingResult bindingResult;

    public MethodArgumentNotValidException(BindingResult bindingResult) {
        super("Validation failed");
        this.bindingResult = bindingResult;
    }

}