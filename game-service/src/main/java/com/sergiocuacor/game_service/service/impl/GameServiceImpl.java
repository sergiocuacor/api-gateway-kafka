package com.sergiocuacor.game_service.service.impl;

import com.sergiocuacor.game_service.commons.entities.GameModel;
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
        System.out.println("Service - Fetching game with ID: " + gameId);
        GameModel game = Optional.of(gameId)
                .flatMap(gameRepository::findById) // flatMap "flattens" the result, avoiding nested Optional objects
                .orElseThrow(() -> new RuntimeException("Couldnt find game with ID " + gameId));

        System.out.println("Service - Found game: " + game);
        return game;
    }

    @Override
    public GameModel createGame(GameModel gameRequest) {
        return Optional.of(gameRequest)
                .map(this::mapToEntity)
                .map(gameRepository::save)
                .orElseThrow(() -> new RuntimeException("An error occurred while creating the game"));
    }



    @Override
    public GameModel updateGame(Long gameId,GameModel gameModel ) {
        return Optional.of(gameId)
                .flatMap(gameRepository::findById)
                .map(existingGame -> updateGameFields(existingGame, gameModel))
                .map(gameRepository::save)
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


    private GameModel mapToEntity(GameModel gameRequest) {
        return GameModel.builder().name(gameRequest.getName()).userId(gameRequest.getUserId()).build();

    }
}
