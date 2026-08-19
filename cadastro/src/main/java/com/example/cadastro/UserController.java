package com.example.cadastro;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")

public class UserController {


    final UserRepository userRepository;

    @GetMapping
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
        Optional <User> userFound = userRepository.findById(id);
        if(userFound.isPresent()){
            return userFound;
        }
    }


    @PostMapping
    public User postUser(@RequestBody User user) {
        return userRepository.save(user);

    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id,@RequestBody User user) {
        Optional<User> savedUser = userRepository.findById(id);
        if(savedUser.isPresent()){
            User updatedUser = savedUser.get();
            updatedUser.setNome(user.getNome());
            updatedUser.setCpf(user.getCpf());
            updatedUser.setEmail(user.getEmail());
            return userRepository.save(updatedUser);
        } else {
            throw new RuntimeException("Couldn't find user ");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            userRepository.deleteById(id);
        } else {
          throw new RuntimeException("Couldn't find user");
        }
    }
}
