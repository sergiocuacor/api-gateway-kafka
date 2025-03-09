package com.sergiocuacor.elegibility_service.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GameEligibleEvent {

	private Long gameId;
	private String name;
	private Long userId;
	private Boolean isEligible;
	
}
