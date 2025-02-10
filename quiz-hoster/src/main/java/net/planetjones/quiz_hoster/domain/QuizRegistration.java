package net.planetjones.quiz_hoster.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class QuizRegistration {

    @Getter
    @Setter
    private String quizSessionId;
    
    @Getter
    @Setter
    private String playerName;

    public QuizRegistration() {
    }



}
