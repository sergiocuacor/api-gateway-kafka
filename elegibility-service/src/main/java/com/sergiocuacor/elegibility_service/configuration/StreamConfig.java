package com.sergiocuacor.elegibility_service.configuration;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sergiocuacor.elegibility_service.common.GameCreatedEvent;
import com.sergiocuacor.elegibility_service.common.GameEligibleEvent;
import com.sergiocuacor.elegibility_service.processors.EligibilityGameProcessor;

import reactor.core.publisher.Flux;

@Configuration
public class StreamConfig {

	@Bean
	Function<Flux<GameCreatedEvent>, Flux<GameEligibleEvent>> gameCreatedBinding(final EligibilityGameProcessor processor){
		return processor::process;
	}
	
}
