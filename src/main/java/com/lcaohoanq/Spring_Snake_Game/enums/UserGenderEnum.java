package com.lcaohoanq.Spring_Snake_Game.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserGenderEnum {

    MALE(1),
    FEMALE(2),
    OTHER(3),
    NOT_PROVIDE(4);

    private final int gender;
}
