package com.example.cadastro.interface_ui.controller;

import com.example.cadastro.application.dto.UserRequestDTO;
import com.example.cadastro.application.dto.UserResponseDTO;
import com.example.cadastro.application.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")

public class UserController {


    final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUserList() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }


    @PostMapping
    public ResponseEntity<UserResponseDTO> postUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO savedUser = userService.save(userRequestDTO);
        return ResponseEntity.created(
                URI.create("/usuario/" + savedUser.id())
        ).body(savedUser);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID id,@Valid @RequestBody UserRequestDTO userRequestDTO) {
            return ResponseEntity.ok(userService.update(userRequestDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
