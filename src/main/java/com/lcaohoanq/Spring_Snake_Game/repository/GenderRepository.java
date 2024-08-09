package com.lcaohoanq.Spring_Snake_Game.repository;

import com.lcaohoanq.Spring_Snake_Game.entity.Gender;
import com.lcaohoanq.Spring_Snake_Game.enums.UserGenderEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Integer> {

    Gender findByGenderName(UserGenderEnum genderName);

    Gender findById(int id);

}
