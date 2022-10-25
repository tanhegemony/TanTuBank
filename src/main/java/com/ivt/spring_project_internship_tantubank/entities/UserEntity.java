/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivt.spring_project_internship_tantubank.entities;

import com.ivt.spring_project_internship_tantubank.enums.AccountStatus;
import com.ivt.spring_project_internship_tantubank.enums.UserStatus;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author TanHegemony
 */
@Entity
@Table(name = "users")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "user_name", length = 20, unique = true, nullable = false)
    private String userName;
    
    @Column(length = 100, nullable = false)
    private String password;
    
    @Column(name = "user_status", length = 10)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.UNACTIVE;
    
    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserRoleEntity> userRoles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CustomerEntity customer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private StaffEntity staff;

    public StaffEntity getStaff() {
        return staff;
    }

    public void setStaff(StaffEntity staff) {
        this.staff = staff;
    }
    
    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
    
    public List<UserRoleEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoleEntity> userRoles) {
        this.userRoles = userRoles;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
