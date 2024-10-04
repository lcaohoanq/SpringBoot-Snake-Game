package com.lcaohoanq.springbootsnakegame.service;

import com.lcaohoanq.springbootsnakegame.model.User;

public interface IUserService{

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    User findById(Long id);
    User findByEmail(String email);

}
