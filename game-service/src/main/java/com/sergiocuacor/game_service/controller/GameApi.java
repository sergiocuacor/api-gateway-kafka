package com.sergiocuacor.game_service.controller;

import com.sergiocuacor.game_service.commons.constants.ApiPathConstants;
import com.sergiocuacor.game_service.commons.entities.GameModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.GAME_ROUTE)
public interface GameApi {

    @GetMapping("/{gameId}")
    ResponseEntity<GameModel> getGame(@PathVariable Long gameId, @RequestHeader("X-User-Id") Long userId);

    @PostMapping("/create")
    ResponseEntity<GameModel> createGame(@RequestBody GameModel gameRequest, @RequestHeader("X-User-Id") Long userId);

    @PutMapping("/update/{gameId}")
    ResponseEntity<Void> updateGame(@PathVariable Long gameId, @RequestBody GameModel gameRequest, @RequestHeader("X-User-Id") Long userId);

    @DeleteMapping("/delete/{gameId}")
    ResponseEntity<Void> removeGame(@PathVariable Long gameId, @RequestHeader("X-User-Id") Long userId);
}
