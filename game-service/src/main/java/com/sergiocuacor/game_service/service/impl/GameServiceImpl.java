package com.sergiocuacor.game_service.service.impl;

import com.sergiocuacor.game_service.commons.entities.GameModel;
import com.sergiocuacor.game_service.commons.exceptions.GameDoesntExistException;
import com.sergiocuacor.game_service.commons.exceptions.GameNotFoundException;
import com.sergiocuacor.game_service.repository.GameRepository;
import com.sergiocuacor.game_service.service.GameService;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.AclEntry;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public GameModel getGame(Long gameId) {
        return Optional.of(gameId)
                .flatMap(gameRepository::findById)
                .orElseThrow(() -> new GameNotFoundException("Game with ID " + gameId + " not found."));
    }

    @Override
    public GameModel createGame(GameModel gameRequest) {
        return Optional.of(gameRequest)
                .map(this::mapToEntity)
                .map(gameRepository::save)
                .orElseThrow(() -> new RuntimeException("An error occurred while creating the game"));
    }

    private GameModel mapToEntity(GameModel gameRequest) {
        return GameModel.builder().name(gameRequest.getName()).build();
    }

    @Override
    public GameModel updateGame(GameModel gameModel, Long gameId) {
        return Optional.of(gameId)
                .flatMap(gameRepository::findById)
                .map(existingGame -> updateGameFields(existingGame, gameModel))
                .orElseThrow(() -> new RuntimeException("An error occurred while trying to update the game"));
    }

    @Override
    public void deleteGame(Long gameId) {
        Optional.of(gameId)
                .ifPresent(gameRepository::deleteById);
    }

    private GameModel updateGameFields(GameModel existingGame, GameModel gameModel) {
        if (existingGame.getName() != null && !existingGame.getName().isEmpty()) {
            existingGame.setName(gameModel.getName());
        }

        return existingGame;
    }


}
