package com.lcaohoanq.springbootsnakegame.repository;

import com.lcaohoanq.springbootsnakegame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByPhone(String phone);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

}
