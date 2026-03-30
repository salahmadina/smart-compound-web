package com.smartcompound.controller;

import com.smartcompound.dto.*;
import com.smartcompound.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserDto>> login(@RequestBody LoginRequest req,
                                                       HttpServletRequest httpReq) {
        try {
            UserDto user = authService.login(req);
            HttpSession session = httpReq.getSession(true);
            session.setAttribute("user", user);
            return ResponseEntity.ok(ApiResponse.ok("Login successful", user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserDto>> signup(@RequestBody SignupRequest req) {
        try {
            UserDto user = authService.signup(req);
            return ResponseEntity.ok(ApiResponse.ok("Account created successfully", user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(HttpServletRequest httpReq) {
        HttpSession session = httpReq.getSession(false);
        if (session != null) session.invalidate();
        return ResponseEntity.ok(ApiResponse.ok("Logged out", null));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserDto>> me(HttpServletRequest httpReq) {
        HttpSession session = httpReq.getSession(false);
        if (session == null || session.getAttribute("user") == null)
            return ResponseEntity.status(401).body(ApiResponse.error("Not authenticated"));
        UserDto user = (UserDto) session.getAttribute("user");
        return ResponseEntity.ok(ApiResponse.ok(user));
    }
}
