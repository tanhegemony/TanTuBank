/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.repository;

import com.ivt.spring_project_internship_tantubank.entities.UserEntity;
import com.ivt.spring_project_internship_tantubank.enums.UserStatus;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author henry
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUserNameLikeAndUserStatus(String userName, UserStatus userStatus);

    UserEntity findByUserName(String userName);

}
