package com.lcaohoanq.Spring_Snake_Game.service;

import com.lcaohoanq.Spring_Snake_Game.entity.User;

public interface IUserService {

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

}
