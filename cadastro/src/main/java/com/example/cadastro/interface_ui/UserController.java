package com.example.cadastro.interface_ui;

import com.example.cadastro.application.dto.UserRequestDTO;
import com.example.cadastro.application.service.UserService;
import com.example.cadastro.domain.repository.UserRepository;
import com.example.cadastro.domain.entity.Users;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")

public class UserController {


    final UserService userService;

    @GetMapping
    public List<Users> getUserList() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable UUID id) {
        return userService.findById(id);
    }


    @PostMapping
    public Users postUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return userService.save(userRequestDTO);

    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable UUID id,@Valid @RequestBody UserRequestDTO userRequestDTO) {
            return userService.update(userRequestDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.delete(id);
    }
}
