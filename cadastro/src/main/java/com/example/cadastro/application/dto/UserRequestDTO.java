package com.example.cadastro.application.dto;

import com.example.cadastro.domain.entity.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record UserRequestDTO (

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 150, message = "Nome deve ter entre 2 e 150 caracteres")
    String nome,

    @NotBlank(message = "CPF é obrigatório")
    @CPF(message = "CPF inválido")
    //@Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos")
    String cpf,

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Size(max = 255, message = "E-mail deve ter no máximo 255 caracteres")
    String email,

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 4, max = 8, message = "Senha deve ter entre 4 e 8 caracteres")
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
