package com.java.basic.setup.domain.player.api;

import com.java.basic.setup.domain.player.entity.Player;
import com.java.basic.setup.domain.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerApi {

    private final PlayerService playerService;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody Player player) {
        player.setPassword(passwordEncoder.encode(player.getPassword()));
        playerService.savePlayer(player);
        return ResponseEntity.ok("Player registered successfully");
    }

    // 로그인 API - Spring Security가 처리
    @PostMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.ok("Login successful");
    }

    // 로그아웃 API - Spring Security가 처리
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("Logout successful");
    }
}
