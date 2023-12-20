package com.java.basic.setup.domain.player.repository;

import com.java.basic.setup.domain.player.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByUsername(String username);
}
