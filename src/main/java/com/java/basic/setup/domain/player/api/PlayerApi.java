package com.java.basic.setup.domain.player.api;

import com.java.basic.setup.domain.player.dto.CreateUserDto;
import com.java.basic.setup.domain.player.dto.LoginDto;
import com.java.basic.setup.domain.player.entity.Player;
import com.java.basic.setup.domain.player.service.PlayerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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



    // 회원가입 API
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody CreateUserDto createUserDto) {
        logger.info("THIS IS SIGNUP API");
        boolean result = playerService.savePlayer(createUserDto);
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
    public ResponseEntity<?> logout(HttpServletRequest request,
                                    HttpServletResponse response) {
        logger.info("THIS IS LOGOUT API");
        Boolean logout = playerService.logout(request, response);
        System.out.println("logout = " + logout);
        return ResponseEntity.ok("Logout successful");
    }
}
