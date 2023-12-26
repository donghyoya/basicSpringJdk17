package com.java.basic.setup.domain.player.api;

import com.java.basic.setup.domain.player.dto.LoginDto;
import com.java.basic.setup.domain.player.entity.Player;
import com.java.basic.setup.domain.player.service.PlayerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class PlayerApi {

    Logger logger = LoggerFactory.getLogger(PlayerApi.class);

    private final PlayerService playerService;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody Player player) {
        logger.info("THIS IS SIGNUP API");
        player.setPassword(passwordEncoder.encode(player.getPassword()));
        playerService.savePlayer(player);
        return ResponseEntity.ok("Player registered successfully");
    }

    // 로그인 API - Spring Security가 처리
    @PostMapping("/login")
    public Boolean login(@RequestBody LoginDto login, HttpServletRequest httpServletRequest) {
        logger.info("THIS IS LOGIN API");
        return playerService.login(login, httpServletRequest);
//        return ResponseEntity.ok("Login successful");
    }

    // 로그아웃 API - Spring Security가 처리
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        logger.info("THIS IS LOGOUT API");
        return ResponseEntity.ok("Logout successful");
    }
}
