package com.lcaohoanq.springbootsnakegame.repository;

import com.lcaohoanq.springbootsnakegame.model.Role;
import com.lcaohoanq.springbootsnakegame.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(UserRoleEnum userRoleEnum);

}