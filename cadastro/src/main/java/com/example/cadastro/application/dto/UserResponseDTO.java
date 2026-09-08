package com.example.cadastro.application.dto;

import com.example.cadastro.domain.entity.Users;

import java.util.UUID;

public record UserResponseDTO (

    UUID id,
    String nome,
    String cpf,
    String email
){
    public static UserResponseDTO fromEntity(Users user) {
        return new UserResponseDTO(
                user.getId(),
                user.getNome(),
                user.getCpf(),
                user.getEmail()
        );
    }
}
