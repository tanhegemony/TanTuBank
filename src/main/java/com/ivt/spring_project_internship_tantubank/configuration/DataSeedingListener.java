/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.configuration;

import com.ivt.spring_project_internship_tantubank.entities.RoleEntity;
import com.ivt.spring_project_internship_tantubank.entities.UserEntity;
import com.ivt.spring_project_internship_tantubank.enums.RoleUser;
import com.ivt.spring_project_internship_tantubank.enums.UserStatus;
import com.ivt.spring_project_internship_tantubank.repository.RoleRepository;
import com.ivt.spring_project_internship_tantubank.repository.UserRepository;
import java.util.Date;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author henry
 */

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent>{
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent  arg0) {
        
        // Admin account
        if (userRepository.findByUserName("admin") == null) {
            UserEntity admin = new UserEntity();
            admin.setCreateDate(new Date());
            admin.setUserStatus(UserStatus.ACTIVE);
            admin.setUserName("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            userRepository.save(admin);
        }

        
          // terel account
        if (userRepository.findByUserName("teller") == null) {
            UserEntity TELLER = new UserEntity();
            TELLER.setCreateDate(new Date());
            TELLER.setUserStatus(UserStatus.ACTIVE);
            TELLER.setUserName("teller");
            TELLER.setPassword(passwordEncoder.encode("123456"));
            userRepository.save(TELLER);
        }
        
        // user account
        if (userRepository.findByUserName("user") == null) {
            UserEntity user = new UserEntity();
            user.setCreateDate(new Date());
            user.setUserStatus(UserStatus.ACTIVE);
            user.setUserName("user");
            user.setPassword(passwordEncoder.encode("123456"));
            userRepository.save(user);
        }
    }
    
}
