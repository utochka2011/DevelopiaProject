package com.example.developia.service;

import com.example.developia.entity.AuthorityEntity;
import com.example.developia.entity.UserEntity;
import com.example.developia.jwt.JwtUtil;
import com.example.developia.repository.AuthorityRepository;
import com.example.developia.repository.UserRepository;
import com.example.developia.request.RegisterRequest;

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

    public void register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("такой username уже существует");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);

        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setUsername(request.getUsername());
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
