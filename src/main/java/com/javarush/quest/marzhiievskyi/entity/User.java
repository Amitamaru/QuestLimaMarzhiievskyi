package com.javarush.quest.marzhiievskyi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "t_user")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Transient
    private Collection<GameSession> games = new ArrayList<>();
    @Transient
    private Collection<GameSession> wins = new ArrayList<>();
    @Transient
    private Collection<GameSession> losses = new ArrayList<>();


}
