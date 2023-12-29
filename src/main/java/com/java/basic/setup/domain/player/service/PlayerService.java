package com.java.basic.setup.domain.player.service;

import com.java.basic.setup.domain.player.dto.CreateUserDto;
import com.java.basic.setup.domain.player.dto.LoginDto;
import com.java.basic.setup.domain.player.entity.Player;
import com.java.basic.setup.domain.player.repository.PlayerRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(PlayerService.class);

    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;

    public Boolean savePlayer(CreateUserDto createUserDto){

        logger.info("create User: "+createUserDto.toString());

        //유저 생성(dto -> Player)
        Player player = new Player();
        player.setCreatePlayer(createUserDto);
//        player.setUserid(createUserDto.getUserid());
//        player.setPassword(createUserDto.getPassword());
//        player.setAge(createUserDto.getAge());

        logger.info("Player info: "+player.toString());

        //비밀번호
        player.setPassword(passwordEncoder.encode(player.getPassword()));
        playerRepository.save(player);

        return true;
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
