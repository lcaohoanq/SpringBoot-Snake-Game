package com.lcaohoanq.Spring_Snake_Game.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailBlockReasonEnum {

    ABUSE("Abuse activity"),
    SPAM("Spam activity"),
    OTHER("Other reason");

    private final String reason;

}
