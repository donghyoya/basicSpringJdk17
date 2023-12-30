package com.java.basic.setup.domain.player.api;

import com.java.basic.setup.domain.player.dto.CreateUserDto;
import com.java.basic.setup.domain.player.entity.Player;
import com.java.basic.setup.domain.player.service.PlayerService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 해당 함수는 만약에 ddl-auto가 create 일때만 실행하면된다
 -> 모든 db 데이터를 날리기 때문이다
 -> 즉 생성할때 자동으로 생성되게끔 해야한다
 */
@Component
@RequiredArgsConstructor
public class InitPlayer {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlMode;

    private final InitPlayerService initPlayerService;
    @PostConstruct
    public void init(){
        if(ddlMode.equals("create")) {
            initPlayerService.init();
        }
    }

    @Component
    static class InitPlayerService{
        @Autowired
        private PlayerService playerService;

        @Transactional
        public void init(){
            CreateUserDto setttingPlayer = new CreateUserDto();

            setttingPlayer.setUserid("test");
            setttingPlayer.setPassword("123123");
            setttingPlayer.setAge(13);

            playerService.savePlayer(setttingPlayer);
        }

    }
}
