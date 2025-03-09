package com.sergiocuacor.elegibility_service.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameCreatedEvent { // Objeto recibido del kafka topic event.game-created

	private Long gameId;
	private String gameName;
	private Long userId;
	
}
