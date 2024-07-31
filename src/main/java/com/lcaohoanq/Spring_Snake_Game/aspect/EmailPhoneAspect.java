package com.lcaohoanq.Spring_Snake_Game.aspect;

import com.lcaohoanq.Spring_Snake_Game.entity.User;
import com.lcaohoanq.Spring_Snake_Game.enums.UserStatusEnum;
import com.lcaohoanq.Spring_Snake_Game.exception.UserHasBeenBannedException;
import com.lcaohoanq.Spring_Snake_Game.exception.UserNotFoundException;
import com.lcaohoanq.Spring_Snake_Game.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmailPhoneAspect {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HttpServletRequest request;

    @Before("execution(* com.lcaohoanq.Spring_Snake_Game.controller.MailController.*(..)) && args(toEmail,..)")
    public void checkIfEmailExists(JoinPoint joinPoint, String toEmail) {
        User user = userRepository.findByEmail(toEmail);
        if (user == null) {
            throw new UserNotFoundException(toEmail);
        } else if (user.getStatus() == UserStatusEnum.BANNED) {
            throw new UserHasBeenBannedException(toEmail);
        }
        request.setAttribute("validatedEmail", user);
    }

    @Before("execution(* com.lcaohoanq.Spring_Snake_Game.controller.PhoneController.*(..)) && args(toPhone,..)")
    public void checkIfPhoneExists(JoinPoint joinPoint, String toPhone) {
        User user = userRepository.findByPhone(toPhone);
        if (user == null) {
            throw new UserNotFoundException(toPhone);
        } else if (user.getStatus() == UserStatusEnum.BANNED) {
            throw new UserHasBeenBannedException(toPhone);
        }
        request.setAttribute("validatedPhone", user);
    }

}
