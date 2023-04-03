package com.project.insurancems.service;

import com.project.insurancems.entity.User;
import com.project.insurancems.payload.request.UpdatePasswordRequest;
import com.project.insurancems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    public void updatePassword(int id, UpdatePasswordRequest password){
        Optional<User> user = userRepository.findById(id);
        this.passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = this.passwordEncoder.encode(password.getPassword());
        user.get().setPassword(encodedPassword);
        userRepository.save(user.get());
    }
}
