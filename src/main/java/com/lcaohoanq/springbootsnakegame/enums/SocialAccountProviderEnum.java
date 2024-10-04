package com.lcaohoanq.springbootsnakegame.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocialAccountProviderEnum {

    GOOGLE(0),
    FACEBOOK(1),
    INSTAGRAM(2),
    TWITTER(3),
    MICROSOFT(4);

    private final int provider;

}
