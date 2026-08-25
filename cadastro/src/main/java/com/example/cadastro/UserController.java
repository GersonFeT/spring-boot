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
    public List<Users> getUserList() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable UUID id) {
        Optional <Users> userFound = userRepository.findById(id);
        if(userFound.isPresent()){
            return userFound.get();
        } else {
            throw new RuntimeException("Couldn't find user");
        }
    }


    @PostMapping
    public Users postUser(@RequestBody Users user) {
        return userRepository.save(user);

    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable UUID id,@RequestBody Users user) {
            Users savedUser = getUserById(id);
            savedUser.setNome(user.getNome());
            savedUser.setCpf(user.getCpf());
            savedUser.setEmail(user.getEmail());

            return userRepository.save(savedUser);

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userRepository.delete(getUserById(id));
    }
}
