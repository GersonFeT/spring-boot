package com.example.cadastro.application.service;

import com.example.cadastro.application.dto.UserRequestDTO;
import com.example.cadastro.application.dto.UserResponseDTO;
import com.example.cadastro.domain.entity.Users;
import com.example.cadastro.domain.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Arrays.stream;

@RequiredArgsConstructor
@Service
public class UserService {


    final UserRepository userRepository;

    public List<UserResponseDTO> findAll() {
        return (userRepository.findAll().stream().map(UserResponseDTO::fromEntity).toList());

    }

    public UserResponseDTO findById(UUID id){
        Optional<Users> userOpt = userRepository.findById(id);
        if(userOpt.isPresent()){
            return UserResponseDTO.fromEntity(userOpt.get());
        } else{
            throw new RuntimeException("Couldn't find user");
        }
    }

    public UserResponseDTO save(UserRequestDTO userRequestDTO) {
        return UserResponseDTO.fromEntity(userRepository.save(userRequestDTO.toEntity()));
    }

    public UserResponseDTO update( UserRequestDTO userRequestDTO, UUID id) {
        Users savedUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find user"));
        savedUser.setNome(userRequestDTO.nome());
        savedUser.setCpf(userRequestDTO.cpf());
        savedUser.setEmail(userRequestDTO.email());
        savedUser.setSenha(userRequestDTO.senha());

        return UserResponseDTO.fromEntity(userRepository.save(savedUser));

    }

    public void delete(UUID id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }else{
            throw new RuntimeException("Couldn't find user");
        }
    }
}
