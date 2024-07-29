package com.lcaohoanq.Spring_Snake_Game.enums;

import lombok.Getter;

@Getter
public enum EmailCategoriesEnum {
    OTP("sendOtp"),
    BLOCK_ACCOUNT("blockAccount"),
    FORGOT_PASSWORD("forgotPassword");

    private final String type;

    EmailCategoriesEnum(String type) {
        this.type = type;
    }

}
