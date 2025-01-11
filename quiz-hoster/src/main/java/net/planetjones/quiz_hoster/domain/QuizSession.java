package net.planetjones.quiz_hoster.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class QuizSession {

    @Getter
    private String id;
    
    @Getter
    private Quiz quiz;
    
    @Getter
    private int currentQuestionIndex;

    public QuizSession(String id, Quiz quiz) {
        this.id = id;
        this.quiz = quiz;
        this.currentQuestionIndex = 0;
    }

}
