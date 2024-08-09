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
        } else if (user.getStatus().getId() == UserStatusEnum.BANNED.getStatus()) {
            throw new UserHasBeenBannedException(toEmail);
        }
        request.setAttribute("validatedEmail", user);
    }

    @Before("execution(* com.lcaohoanq.Spring_Snake_Game.controller.PhoneController.*(..)) && args(toPhone,..)")
    public void checkIfPhoneExists(JoinPoint joinPoint, String toPhone) {
        User user = userRepository.findByPhone(toPhone);
        if (user == null) {
            throw new UserNotFoundException(toPhone);
        } else if (user.getStatus().getId() == UserStatusEnum.BANNED.getStatus()) {
            throw new UserHasBeenBannedException(toPhone);
        }
        request.setAttribute("validatedPhone", user);
    }

    // Combined pointcut for both UserController and ForgotPasswordController
    @Before("execution(* com.lcaohoanq.Spring_Snake_Game.controller.UserController.*(..)) && args(email_phone,..) || execution(* com.lcaohoanq.Spring_Snake_Game.controller.ForgotPasswordController.*(..)) && args(email_phone,..)")
    public void checkIfAccountExists(JoinPoint joinPoint, String email_phone) {
        checkAccount(email_phone);
    }

    private void checkAccount(String email_phone) {
        User user = userRepository.findByEmail(email_phone);
        if(user == null){
            throw new UserNotFoundException(email_phone);
        }
        if (user.getStatus().getId() == UserStatusEnum.BANNED.getStatus()) {
            throw new UserHasBeenBannedException(email_phone);
        }
        request.setAttribute("validatedAccount", user);
    }

}
