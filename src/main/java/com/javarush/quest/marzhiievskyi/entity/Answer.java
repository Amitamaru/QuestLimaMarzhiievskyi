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
@Table(name = "answer")
public class Answer implements AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @Column(name = "question_id")
    private Long questionId;
    @Column(name = "next_question_id")
    private Long nextQuestionId;
}
