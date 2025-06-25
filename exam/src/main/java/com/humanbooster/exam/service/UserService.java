package com.humanbooster.exam.service;

import com.humanbooster.exam.dto.UserDTO;
import com.humanbooster.exam.entity.User;
import com.humanbooster.exam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private UserRepository userRepository;

    public List<User> findAll(){return userRepository.findAll();}

    public Optional<User> findById(Long id){return userRepository.findById(id);}

    public User save(User user){return userRepository.save(user);}

    public User update(Long id, User user){
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteById(Long id){userRepository.deleteById(id);}

    public UserDTO toDTO(User user){
        if (user==null)return null;
        return new UserDTO(user.getUsername());
    }

    public User toEntity(UserDTO dto){
        if (dto==null)return null;
        User user = new User();
        user.setUsername(dto.getUsername());
        return user;
    }
}
