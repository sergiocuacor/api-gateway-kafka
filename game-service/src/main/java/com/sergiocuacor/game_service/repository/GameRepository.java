package com.sergiocuacor.game_service.repository;

import com.sergiocuacor.game_service.commons.entities.GameModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameModel, Long> {
}
