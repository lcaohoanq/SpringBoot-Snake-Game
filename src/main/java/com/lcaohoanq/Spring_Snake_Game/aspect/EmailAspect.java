package com.lcaohoanq.Spring_Snake_Game.aspect;

import com.lcaohoanq.Spring_Snake_Game.entity.User;
import com.lcaohoanq.Spring_Snake_Game.exception.UserNotFoundException;
import com.lcaohoanq.Spring_Snake_Game.repository.UserRepository;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmailAspect {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletRequest request;

    @Before("execution(* com.lcaohoanq.Spring_Snake_Game.controller.*.*(..)) && args(toEmail,..)")
    public void checkIfEmailExists(JoinPoint joinPoint, String toEmail) {
        User user = userRepository.findByEmail(toEmail);
        if (user == null) {
            throw new UserNotFoundException(toEmail);
        }
        request.setAttribute("validatedUser", user);
    }
}
