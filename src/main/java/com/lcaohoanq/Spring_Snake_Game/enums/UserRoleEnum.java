package com.lcaohoanq.Spring_Snake_Game.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRoleEnum {

    USER(1),
    USER_GOLD(2),
    USER_PREMIUM(3),
    EMPLOYEE(4),
    ADMIN(5);

    private final int role;

}
