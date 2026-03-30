package com.smartcompound.service;

import com.smartcompound.dto.UserDto;
import com.smartcompound.entity.User;
import com.smartcompound.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDto> getAllResidents() {
        return userRepository.findByRole("RESIDENT")
                .stream().map(UserDto::from).toList();
    }

    public UserDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return UserDto.from(user);
    }

    public void deleteResident(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (!user.getRole().equals("RESIDENT"))
            throw new IllegalArgumentException("Can only remove residents");
        userRepository.delete(user);
    }
}
