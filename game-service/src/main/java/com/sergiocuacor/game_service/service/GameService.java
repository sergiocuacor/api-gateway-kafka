package com.sergiocuacor.game_service.service;

import com.sergiocuacor.game_service.commons.entities.GameModel;

public interface GameService {


    GameModel getGame(Long gameId);
    GameModel createGame(GameModel gameModel);
    GameModel updateGame(Long gameId, GameModel gameModel);

    void deleteGame(Long gameId);

}
