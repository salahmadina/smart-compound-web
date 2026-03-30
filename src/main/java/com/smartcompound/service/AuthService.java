package com.smartcompound.service;

import com.smartcompound.dto.LoginRequest;
import com.smartcompound.dto.SignupRequest;
import com.smartcompound.dto.UserDto;
import com.smartcompound.entity.User;
import com.smartcompound.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public UserDto login(LoginRequest req) {
        User user = userRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return UserDto.from(user);
    }

    public UserDto signup(SignupRequest req) {
        validateSignup(req);

        User user = User.builder()
                .username(req.getUsername().trim())
                .passwordHash(passwordEncoder.encode(req.getPassword()))
                .fullName(req.getFullName().trim())
                .email(req.getEmail().trim().toLowerCase())
                .phone(req.getPhone().trim())
                .nationalId(req.getNationalId().trim())
                .age(req.getAge())
                .role(req.getRole().toUpperCase())
                .dependants(req.getDependants())
                .apartmentNum(req.getApartmentNum())
                .buildingNum(req.getBuildingNum())
                .build();

        userRepository.save(user);
        return UserDto.from(user);
    }

    private void validateSignup(SignupRequest req) {
        if (req.getUsername() == null || req.getUsername().isBlank())
            throw new IllegalArgumentException("Username is required");
        if (req.getPassword() == null || req.getPassword().length() < 3)
            throw new IllegalArgumentException("Password must be at least 3 characters");
        if (req.getFullName() == null || req.getFullName().isBlank())
            throw new IllegalArgumentException("Full name is required");

        String email = req.getEmail() == null ? "" : req.getEmail().trim();
        if (!EMAIL_PATTERN.matcher(email).matches())
            throw new IllegalArgumentException("Invalid email format");

        String phone = req.getPhone() == null ? "" : req.getPhone().trim();
        if (!phone.matches("\\d{11}"))
            throw new IllegalArgumentException("Phone must be exactly 11 digits");

        String nid = req.getNationalId() == null ? "" : req.getNationalId().trim();
        if (!nid.matches("\\d{14}"))
            throw new IllegalArgumentException("National ID must be exactly 14 digits");

        if (req.getAge() < 18)
            throw new IllegalArgumentException("Age must be at least 18");

        String role = req.getRole() == null ? "" : req.getRole().toUpperCase();
        if (!role.equals("RESIDENT") && !role.equals("STAFF"))
            throw new IllegalArgumentException("Role must be RESIDENT or STAFF");

        if (userRepository.existsByUsername(req.getUsername()))
            throw new IllegalArgumentException("Username already taken");
        if (userRepository.existsByEmail(email))
            throw new IllegalArgumentException("Email already registered");
        if (userRepository.existsByPhone(phone))
            throw new IllegalArgumentException("Phone number already registered");
        if (userRepository.existsByNationalId(nid))
            throw new IllegalArgumentException("National ID already registered");
    }
}
