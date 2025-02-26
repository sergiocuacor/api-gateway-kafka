package com.sergiocuacor.game_service.controller;

import com.sergiocuacor.game_service.commons.constants.ApiPathConstants;
import com.sergiocuacor.game_service.commons.entities.GameModel;
import com.sergiocuacor.game_service.commons.entities.GameResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.GAME_ROUTE)
public interface GameApi {

    @GetMapping("/{gameId}")
    public ResponseEntity<GameModel> getGame(@PathVariable Long gameId);

    @PostMapping
    public ResponseEntity<GameModel> createGame(@RequestBody GameModel gameModel);

    @PutMapping("/update/{gameId}")
    public ResponseEntity<Void> updateGame(@RequestBody GameModel gameModel, @PathVariable Long gameId);

    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long gameId);
}
