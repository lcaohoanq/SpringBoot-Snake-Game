package com.lcaohoanq.Spring_Snake_Game.service;

import com.lcaohoanq.Spring_Snake_Game.model.User;

public interface IUserService{

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    User findById(Long id);
    User findByEmail(String email);

}
