package com.java.basic.setup.domain.player.service;

import com.java.basic.setup.domain.player.entity.Player;
import com.java.basic.setup.domain.player.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * PlayerService와 SecurityConfig 간에 순환 참조(circular reference) 가 발생
 * PlayerService가 SecurityConfig에 의존와 동시에
 * SecurityConfig가 PlayerService에 의존하는 서로 양방향 의존관계가 걸려있음
 * 이를 해결하기 위해 컴포넌트 분리시키기 위한 Service
 */

@Service
public class CustomUserDetailService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

    private final PlayerRepository playerRepository;

    @Autowired
    public CustomUserDetailService(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("THIS IS CustomUserDetailService");
        Player player = playerRepository.findByUserid(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return User.builder()
                .username(player.getUserid())
                .password(player.getPassword())
                .authorities("ROLE_USER") // 권한 설정
                .build();
    }
}
