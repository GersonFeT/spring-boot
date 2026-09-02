package com.example.cadastro.application.service;

import com.example.cadastro.application.dto.UserRequestDTO;
import com.example.cadastro.domain.entity.Users;
import com.example.cadastro.domain.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {


    final UserRepository userRepository;

    public List<Users> findAll() {
        return userRepository.findAll();

    }

    public Users findById(UUID id){
        Optional<Users> userFound = userRepository.findById(id);
        if(userFound.isPresent()){
            return userFound.get();
        } else {
            throw new RuntimeException("Couldn't find user");
        }
    }

    public Users save(UserRequestDTO userRequestDTO) {
        return userRepository.save(userRequestDTO.toEntity());
    }

    public Users update( UserRequestDTO userRequestDTO, UUID id) {
        Users savedUser = findById(id);
        savedUser.setNome(userRequestDTO.nome());
        savedUser.setCpf(userRequestDTO.cpf());
        savedUser.setEmail(userRequestDTO.email());
        savedUser.setSenha(userRequestDTO.senha());

        return userRepository.save(savedUser);

    }

    public void delete(UUID id) {
        userRepository.delete(findById(id));
    }
}
