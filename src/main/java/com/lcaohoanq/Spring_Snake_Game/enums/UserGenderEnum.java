package com.lcaohoanq.Spring_Snake_Game.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserGenderEnum {

    MALE("M"),
    FEMALE("F"),
    OTHER("O"),
    NOT_PROVIDE("N");

    private final String gender;
}
