package com.lcaohoanq.springbootsnakegame.repository;

import com.lcaohoanq.springbootsnakegame.model.SocialAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialAccountRepository extends JpaRepository<SocialAccount, Long> {



}
