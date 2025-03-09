package com.sergiocuacor.elegibility_service.services;

import com.sergiocuacor.elegibility_service.common.GameCreatedEvent;
import com.sergiocuacor.elegibility_service.common.GameEligibleEvent;

import reactor.core.publisher.Mono;

public interface GameEligibleService {

	Mono<GameEligibleEvent> elegibilityGame(GameCreatedEvent gameCreatedEvent);
}
