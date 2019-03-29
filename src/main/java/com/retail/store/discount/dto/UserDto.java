package com.retail.store.discount.dto;

import com.retail.store.discount.enumeration.UserType;

import java.io.Serializable;
import java.time.LocalDate;

public class UserDto implements Serializable {

    private Long id;
    private String name;
    private UserType userType;
    private LocalDate startDate;

    public Long getId() {
        return id;
    }

    public UserDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserDto setName(String name) {
        this.name = name;
        return this;
    }

    public UserType getUserType() {
        return userType;
    }

    public UserDto setUserType(UserType userType) {
        this.userType = userType;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public UserDto setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userType='" + userType + '\'' +
                ", startDate='" + startDate + '\'' +
                '}';
    }
}
