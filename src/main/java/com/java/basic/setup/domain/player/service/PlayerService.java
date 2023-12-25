package com.java.basic.setup.domain.player.service;

import com.java.basic.setup.domain.player.dto.LoginDto;
import com.java.basic.setup.domain.player.entity.Player;
import com.java.basic.setup.domain.player.repository.PlayerRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;

    public Player savePlayer(Player player){
        player.setPassword(passwordEncoder.encode(player.getPassword()));
        return playerRepository.save(player);
    }

    public boolean login(LoginDto login, HttpServletRequest httpServletRequest) {

        String userid = login.getUserid();
        Player player = null;

        Optional<Player> byUserid = playerRepository.findByUserid(userid);
        
        if(byUserid.isEmpty()){
            return false;
        }
        player = byUserid.get();

        if(player.getPassword() != login.getPassword()){
            return false;
        }

        return true;
    }
}
