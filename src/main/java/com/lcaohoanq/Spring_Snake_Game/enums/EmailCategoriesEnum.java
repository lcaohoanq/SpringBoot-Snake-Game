package com.lcaohoanq.Spring_Snake_Game.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailCategoriesEnum {
    OTP("sendOtp"),
    BLOCK_ACCOUNT("blockAccount"),
    FORGOT_PASSWORD("forgotPassword");

    private final String type;
}
