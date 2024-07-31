package com.lcaohoanq.Spring_Snake_Game.entity;

import com.lcaohoanq.Spring_Snake_Game.enums.UserGenderEnum;
import com.lcaohoanq.Spring_Snake_Game.enums.UserRoleEnum;
import com.lcaohoanq.Spring_Snake_Game.enums.UserStatusEnum;
import com.lcaohoanq.Spring_Snake_Game.util.PBKDF2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.time.LocalDateTime;

public class DemoEntity {

    public static void main(String[] args) {
        // Create an EntityManagerFactory and EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Spring-Snake-Game-PU");
        EntityManager em = emf.createEntityManager();

        // Start a transaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            // Create a User object first
            User user = new User("hoang", "luu", "hoangdz1604@gmail.com",
                "0905288699",
                new PBKDF2().hash("Iloveyou123!".toCharArray()), "1999-04-16", "Ha Noi",
                UserGenderEnum.FEMALE,
                UserRoleEnum.USER, UserStatusEnum.VERIFIED,
                LocalDateTime.now().toString(), LocalDateTime.now().toString(),
                null, 0);

            // Persist the User object first
            User managedUser = em.merge(user);

            // Create a Score object and associate it with the existing User
            Score score = new Score(1, 1,
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(), managedUser);

            // Persist the Score object
            em.merge(score);

            // Commit the transaction
            transaction.commit();

            // Print the user and score information
            System.out.println("User and Score have been persisted successfully:");
            System.out.println(user);
            System.out.println(score);

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
