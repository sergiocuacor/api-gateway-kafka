package com.sergiocuacor.game_service.controller.impl;

import com.sergiocuacor.game_service.commons.constants.ApiPathConstants;
import com.sergiocuacor.game_service.commons.entities.GameModel;
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
    public ResponseEntity<GameModel> getGame(Long gameId) {
        return ResponseEntity.ok(gameService.getGame(gameId));
    }

    @Override
    public ResponseEntity<GameModel> createGame(GameModel gameModel) {
        return ResponseEntity.ok(gameService.createGame(gameModel));
    }

    @Override
    public ResponseEntity<Void> updateGame(GameModel gameModel, Long gameId) {
        gameService.updateGame(gameModel,gameId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteGame(Long gameId) {
        gameService.deleteGame(gameId);
        return ResponseEntity.noContent().build();
    }
}
