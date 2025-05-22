package com.example.developia.service;

import com.example.developia.entity.AuthorityEntity;
import com.example.developia.entity.UserEntity;
import com.example.developia.jwt.JwtUtil;
import com.example.developia.repository.AuthorityRepository;
import com.example.developia.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(UserEntity userEntity) {
        if (userRepository.findByUsername(userEntity.getUsername()).isPresent()) {
            throw new RuntimeException("такой username уже существует");
        }

        UserEntity user = new UserEntity();
        user.setUsername(userEntity.getUsername());
        user.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        user.setEnabled(true);
        user.setEmail(userEntity.getEmail());
        user.setPhoneNumber(userEntity.getPhoneNumber());
        userRepository.save(user);

        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setUsername(userEntity.getUsername());
        authorityEntity.setAuthority("ROLE_USER");
        authorityRepository.save(authorityEntity);
    }

    public String login(String username, String password) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("пользователь не найден"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("неверный пароль");
        }

        return jwtUtil.generateToken(username);
    }

    public void logout(String token) {
        SecurityContextHolder.clearContext();
        jwtUtil.invalidateToken(token);
    }

    public UserEntity getUserInfo(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("пользователь не найден"));
    }
}
