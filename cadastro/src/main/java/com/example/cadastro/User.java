package com.example.cadastro;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User {
    private String nome;
    private String cpf;
    private String email;
}
