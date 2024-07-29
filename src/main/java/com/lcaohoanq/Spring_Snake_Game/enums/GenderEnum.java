package com.lcaohoanq.Spring_Snake_Game.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum GenderEnum {

    MALE("M"),
    FEMALE("F"),
    OTHER("O");

    private final String gender;
}
