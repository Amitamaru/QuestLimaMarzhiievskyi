package com.javarush.quest.marzhiievskyi.service;

import com.javarush.quest.marzhiievskyi.entity.Question;
import com.javarush.quest.marzhiievskyi.repository.QuestionRepository;
import com.javarush.quest.marzhiievskyi.repository.Repository;

import java.util.Collection;
import java.util.Optional;


public enum QuestionService {
    QUESTION_SERVICE;

    private final Repository<Question> questionRepository = new QuestionRepository();

    public void create(Question question) {
        questionRepository.create(question);
    }

    public void update(Question question) {
        questionRepository.update(question);
    }

    public void delete(Question question) {
        questionRepository.delete(question);
    }

    public Question get(Long questionId) {
        return questionRepository.get(questionId);
    }

    public Optional<Question> get(String text) {
        Question questionToFind = Question.builder()
                .text(text)
                .build();
        return questionRepository.find(questionToFind).findAny();
    }

    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }


}
