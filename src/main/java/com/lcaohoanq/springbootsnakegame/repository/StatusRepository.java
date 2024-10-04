package com.lcaohoanq.springbootsnakegame.repository;

import com.lcaohoanq.springbootsnakegame.model.Status;
import com.lcaohoanq.springbootsnakegame.enums.UserStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {

    Status findByStatusName(UserStatusEnum genderName);

    Status findById(int id);

}