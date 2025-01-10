package net.planetjones.quiz_hoster.domain;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    
    private Long id;
    private String description;
    private List<QuizQuestion> questions = new ArrayList<>();

    public Quiz(Long id, String description, List<QuizQuestion> questions) {
        this.id = id;
        this.description = description;
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<QuizQuestion> getQuestions() {
        return questions;
    }
    
}
