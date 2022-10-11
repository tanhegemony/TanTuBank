/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.service;

import com.ivt.spring_project_internship_tantubank.entities.RoleEntity;
import com.ivt.spring_project_internship_tantubank.entities.UserEntity;
import com.ivt.spring_project_internship_tantubank.enums.Nation;
import com.ivt.spring_project_internship_tantubank.enums.UserStatus;
import com.ivt.spring_project_internship_tantubank.repository.RoleRepository;
import com.ivt.spring_project_internship_tantubank.repository.UserRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author henry
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    
    @Autowired
    private UserRepository UserRepository ;
    
    @Autowired
    private  RoleRepository RoleRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity user = UserRepository.findByUserNameLikeAndUserStatus(userName, UserStatus.ACTIVE);
        if(user == null){
            throw  new UsernameNotFoundException(userName);
        }
        
        Set<RoleEntity> roleNames = RoleRepository.findRolesByUserName(userName);
        Set<GrantedAuthority> grantList = new HashSet<>();
        if (!CollectionUtils.isEmpty(roleNames)) {
            for (RoleEntity role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName().toString());
                grantList.add(authority);
            }
        }
        return (UserDetails) new User(user.getUserName(), user.getPassword(), grantList);
    }
    
}
