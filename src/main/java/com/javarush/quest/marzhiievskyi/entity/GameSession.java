package com.javarush.quest.marzhiievskyi.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "game_session")
public class GameSession implements AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "t_user_id")
    private Long userId;
    @Column(name = "quest_id")
    private Long questId;
    @Column(name = "current_question_id")
    private Long currentQuestionId;
    @Column(name = "game_state")
    @Enumerated(EnumType.STRING)
    private GameState gameState;
}
