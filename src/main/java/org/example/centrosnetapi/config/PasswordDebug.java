package org.example.centrosnetapi.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordDebug {

    @Autowired
    PasswordEncoder passwordEncoder;

    /*@PostConstruct
    public void run() {
        System.out.println("ADMIN HASH => " + passwordEncoder.encode("admin123"));
    }

     */
}