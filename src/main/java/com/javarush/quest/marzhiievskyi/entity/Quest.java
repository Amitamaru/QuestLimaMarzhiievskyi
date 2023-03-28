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
@Table(name = "quest")
public class Quest implements AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "text")
    private String startingText;
    @Column(name = "starting_question_id")
    private Long startQuestionId;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "quest_id")
    private Collection<Question> questions = new ArrayList<>();

//    private Collection<User> players = new ArrayList<>();

}
