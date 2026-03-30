package com.smartcompound.controller;

import com.smartcompound.dto.ApiResponse;
import com.smartcompound.dto.UserDto;
import com.smartcompound.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/residents")
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllResidents() {
        return ResponseEntity.ok(ApiResponse.ok(userService.getAllResidents()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(ApiResponse.ok(userService.getById(id)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteResident(@PathVariable Long id) {
        try {
            userService.deleteResident(id);
            return ResponseEntity.ok(ApiResponse.ok("Resident removed", null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}
