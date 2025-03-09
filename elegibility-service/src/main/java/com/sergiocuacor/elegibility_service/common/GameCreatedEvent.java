package com.sergiocuacor.elegibility_service.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GameCreatedEvent {

	private Long gameId;
	private String gameName;
	private Long userId;
	
}
