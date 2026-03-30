package com.smartcompound.dto;

import com.smartcompound.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserDto implements Serializable {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private String nationalId;
    private int age;
    private String role;
    private String dependants;
    private Integer apartmentNum;
    private Integer buildingNum;
    private LocalDateTime createdAt;

    public static UserDto from(User u) {
        UserDto dto = new UserDto();
        dto.id = u.getId();
        dto.username = u.getUsername();
        dto.fullName = u.getFullName();
        dto.email = u.getEmail();
        dto.phone = u.getPhone();
        dto.nationalId = u.getNationalId();
        dto.age = u.getAge();
        dto.role = u.getRole();
        dto.dependants = u.getDependants();
        dto.apartmentNum = u.getApartmentNum();
        dto.buildingNum = u.getBuildingNum();
        dto.createdAt = u.getCreatedAt();
        return dto;
    }
}
