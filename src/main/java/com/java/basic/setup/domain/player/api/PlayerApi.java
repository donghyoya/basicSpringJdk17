package com.java.basic.setup.domain.player.api;

import com.java.basic.setup.domain.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlayerApi {

    private final PlayerService playerService;

    @PostMapping(value = "/login")
    public Boolean PlayerLogin(@)
}
