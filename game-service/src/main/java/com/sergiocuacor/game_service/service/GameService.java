package com.sergiocuacor.game_service.service;

import com.sergiocuacor.game_service.commons.entities.GameModel;

public interface GameService {


    GameModel getGame(Long gameId);
    GameModel createGame(GameModel gameRequest);
        GameModel updateGame(GameModel gameModel, Long gameId);
    void deleteGame(Long gameId);

}
