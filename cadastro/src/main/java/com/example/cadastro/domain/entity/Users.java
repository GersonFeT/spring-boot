package com.example.cadastro.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
        @UniqueConstraint(name = "uk_user_cpf", columnNames = "cpf"),
        @UniqueConstraint(name = "uk_user_email", columnNames = "email")
        },
        indexes = {
                @Index(name = "idx_user_name", columnList = "nome"),
                @Index(name = "idx_user_email", columnList = "email")
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;


    @Column(name = "cpf", nullable = false, length = 11, unique = true)
    private String cpf;


    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "senha", nullable = false, length = 8)
    private String senha;

    @PrePersist
    @PreUpdate
    private void normalizarDados(){
        if(this.nome != null){
            this.nome = this.nome.trim();
        }
        if(this.cpf != null){
            this.cpf = this.cpf.replaceAll("\\D", "");
        }
        if(this.email != null){
            this.email = this.email.trim().toLowerCase();
        }
    }
}
