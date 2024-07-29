package com.lcaohoanq.Spring_Snake_Game.controller;

import com.lcaohoanq.Spring_Snake_Game.exception.MethodArgumentNotValidException;
import com.lcaohoanq.Spring_Snake_Game.exception.UserNotFoundException;
import com.lcaohoanq.Spring_Snake_Game.model.User;
import com.lcaohoanq.Spring_Snake_Game.repository.UserRepository;
import com.lcaohoanq.Spring_Snake_Game.util.ValidatorUtil;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    List<User> all() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    User getById(@PathVariable Long id) {

        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id)) ;
    }

    @PostMapping("/users")
    User createNew(@Valid @RequestBody User newUser, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(bindingResult);
        }
        return userRepository.save(newUser);
    }

    @DeleteMapping("/users/{id}")
    void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/users/{id}")
    User updateOrCreate(@RequestBody User newUser, @PathVariable Long id) {

        return userRepository.findById(id)
            .map(item -> {
                item.setFirstName(newUser.getFirstName());
                return userRepository.save(item);
            })
            .orElseGet(() -> {
                newUser.setId(id);
                return userRepository.save(newUser);
            });
    }

}
