package com.example.demo.service;

import com.example.demo.model.UserEntity;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // UserEntity를 DB에 저장
    public UserEntity create(final UserEntity userEntity) {
        if(userEntity == null || userEntity.getUsername() == null) {
            throw new RuntimeException("Invalid arguments");
        }
        final String username = userEntity.getUsername();
        // 동일한 username이 DB에 존재한다면
        if(userRepository.existsByUsername(username)) {
            log.warn("Usernmae already exists {}", username);
            throw new RuntimeException("Username already exists");
        }

        // UserEntity를 DB에 저장
        return userRepository.save(userEntity);
    }

    // username, password를 이용해서 UserEntity를 검색
    public UserEntity getByCredentials(final String username, final String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
