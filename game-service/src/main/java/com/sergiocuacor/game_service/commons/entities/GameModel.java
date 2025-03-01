package com.sergiocuacor.game_service.commons.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "games")
public class GameModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String name;

}
