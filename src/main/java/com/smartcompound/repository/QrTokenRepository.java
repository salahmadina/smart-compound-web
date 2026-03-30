package com.smartcompound.repository;

import com.smartcompound.entity.QrToken;
import com.smartcompound.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QrTokenRepository extends JpaRepository<QrToken, Long> {
    Optional<QrToken> findByUser(User user);
    Optional<QrToken> findByToken(String token);
}
