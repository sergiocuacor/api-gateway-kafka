package com.sergiocuacor.game_service.controller.impl;

import com.sergiocuacor.game_service.commons.constants.ApiPathConstants;
import com.sergiocuacor.game_service.commons.entities.GameModel;
import com.sergiocuacor.game_service.commons.exceptions.UnauthorizedException;
import com.sergiocuacor.game_service.controller.GameApi;
import com.sergiocuacor.game_service.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.GAME_ROUTE)
public class GameController implements GameApi {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @Override
    public ResponseEntity<GameModel> getGame(Long gameId, Long userId) {

        System.out.println("Controller - Requesting game with ID: " + gameId);
        System.out.println("Controller - User requesting: " + userId);

        GameModel game = gameService.getGame(gameId);
        System.out.println("Controller - Game retrieved: " + game);


        if (game.getUserId() == null) {
            throw new RuntimeException("ERROR: Game has no owner");
        }

        if (!game.getUserId().equals(userId)) {
            throw new UnauthorizedException("You don't have permission to view this game");
        }

        return ResponseEntity.ok(game);
    }

    @Override
    public ResponseEntity<GameModel> createGame(GameModel gameRequest, Long userId) {
        System.out.println("X-User-Id: " + userId);
        System.out.println(userId.getClass());
        gameRequest.setUserId(userId);
        return ResponseEntity.ok(gameService.createGame(gameRequest));
    }

    @Override
    public ResponseEntity<Void> updateGame(Long gameId, GameModel gameRequest, Long userId) {
        GameModel existingGame = gameService.getGame(gameId);
        if (!existingGame.getUserId().equals(userId)) {
            throw new UnauthorizedException("You don't have permission to modify this game");
        }
        gameService.updateGame(gameId, gameRequest);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> removeGame(Long gameId, Long userId) {
        GameModel existingGame = gameService.getGame(gameId);
        if (!existingGame.getUserId().equals(userId)) {
            throw new UnauthorizedException("You don't have permission to remove this game.");
        }
        gameService.deleteGame(gameId);
        return ResponseEntity.noContent().build();
    }
}
