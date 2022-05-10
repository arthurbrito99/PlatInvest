package com.arthurb.PlatInvest.controller;

import com.arthurb.PlatInvest.model.Company;
import com.arthurb.PlatInvest.model.User;
import com.arthurb.PlatInvest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/user/create")
    public ResponseEntity<User> createUser(@Validated @RequestBody User user){
        try {
            return ResponseEntity.ok(userRepository.save(user));
        }   catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @Validated @RequestBody User user){
        User userToUpdate = userRepository.findById(id).orElse(null);
        if(userToUpdate != null){
            userToUpdate.setName(user.getName());
            userToUpdate.setCpf(user.getCpf());
            userToUpdate.setInvestment(user.getInvestment());
            return ResponseEntity.ok(userRepository.save(userToUpdate));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/cpf")
    public ResponseEntity<User> getUserByCpf(@RequestParam("cpf") String cpf){
        try {
            return ResponseEntity.ok(userRepository.findByCpf(cpf));
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
