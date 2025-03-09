package com.sergiocuacor.elegibility_service.processors;

import org.springframework.stereotype.Component;

import com.sergiocuacor.elegibility_service.common.GameCreatedEvent;
import com.sergiocuacor.elegibility_service.common.GameEligibleEvent;
import com.sergiocuacor.elegibility_service.services.GameEligibleService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class EligibilityGameProcessor {

	private GameEligibleService gameEligibleService;

	public EligibilityGameProcessor(GameEligibleService gameEligibleService) {
		super();
		this.gameEligibleService = gameEligibleService;
	}
	
	public Flux<GameEligibleEvent> process(Flux<GameCreatedEvent> gameCreatedEventFlux){
		log.info("Processing event: {}", gameCreatedEventFlux);
		return gameCreatedEventFlux.doOnNext(given -> log.info("Entry event: {}",given))
				.flatMap(gameEligibleService::elegibilityGame)
				.onErrorContinue(this::handleError);
	}
	
	private void handleError(Throwable throwable, Object object) {
		log.error("Error processing event: {}", object, throwable);
	}
}
