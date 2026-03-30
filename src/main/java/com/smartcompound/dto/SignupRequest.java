package com.smartcompound.dto;

import lombok.Data;

@Data
public class SignupRequest {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String nationalId;
    private int age;
    private String role;       // RESIDENT or STAFF
    private String dependants;
    private Integer apartmentNum;
    private Integer buildingNum;
}
