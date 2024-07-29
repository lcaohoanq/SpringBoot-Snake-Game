package com.lcaohoanq.Spring_Snake_Game.repository;

import com.lcaohoanq.Spring_Snake_Game.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByPhone(String phone);

}
