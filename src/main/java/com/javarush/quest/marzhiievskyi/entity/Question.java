package com.javarush.quest.marzhiievskyi.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "question")
public class Question implements AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @Column(name = "quest_id")
    private Long questId;
    @Column(name = "game_state")
    @Enumerated(EnumType.STRING)
    private GameState gameState;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Collection<Answer> answerList = new ArrayList<>();

}
