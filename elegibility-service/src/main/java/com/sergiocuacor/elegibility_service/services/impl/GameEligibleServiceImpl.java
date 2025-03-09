package com.sergiocuacor.elegibility_service.services.impl;

import org.springframework.stereotype.Service;

import com.sergiocuacor.elegibility_service.common.GameCreatedEvent;
import com.sergiocuacor.elegibility_service.common.GameEligibleEvent;
import com.sergiocuacor.elegibility_service.services.GameEligibleService;

import reactor.core.publisher.Mono;

@Service
public class GameEligibleServiceImpl implements GameEligibleService{

	@Override
	public Mono<GameEligibleEvent> elegibilityGame(GameCreatedEvent gameCreatedEvent) {
		return Mono.just(gameCreatedEvent)
				.flatMap(this::checkIsEligible)
				.map(givenCreated -> GameEligibleEvent.builder()
						.gameId(gameCreatedEvent.getGameId())
						.gameName(gameCreatedEvent.getGameName())
						.userId(gameCreatedEvent.getUserId())
						.isEligible(true)
						.build());
	}
	
	private Mono<GameCreatedEvent> checkIsEligible(GameCreatedEvent gameCreatedEvent){
		return Mono.just(gameCreatedEvent)
				.filter(given -> !given.getGameName().isBlank()) // comprobamos que el name no esté vacío para ser eligible
				.map(given -> gameCreatedEvent)
				;
	}

}
