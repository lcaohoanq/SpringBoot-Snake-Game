package com.lcaohoanq.Spring_Snake_Game.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocialAccountProviderEnum {

    GOOGLE(0),
    FACEBOOK(1);

    private final int provider;

}
