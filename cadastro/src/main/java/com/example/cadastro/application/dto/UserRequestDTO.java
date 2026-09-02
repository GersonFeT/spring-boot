package com.example.cadastro.application.dto;

import com.example.cadastro.domain.entity.Users;

public record UserRequestDTO (
    String nome,
    String cpf,
    String email,
    String senha
) {
    public Users toEntity(){
        return new Users(
                null,
                nome,
                cpf,
                email,
                senha
        );
    }
}
