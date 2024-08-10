package com.lcaohoanq.Spring_Snake_Game.repository;

import com.lcaohoanq.Spring_Snake_Game.entity.SocialAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialAccountRepository extends JpaRepository<SocialAccount, Long> {



}
