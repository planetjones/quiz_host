package net.planetjones.quiz_hoster.domain;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Quiz {
    
    @Getter
    @Setter
    private Long id;
    
    @Getter
    @Setter
    private String description;
    
    @Getter
    @Setter
    private List<Question> questions;

    @EqualsAndHashCode
    @ToString
    public static class Question {

        @Getter
        @Setter
        private Long id;
        
        @Getter
        @Setter
        private String question;
        
        @Getter
        @Setter
        private String type;
        
        @Getter
        @Setter
        private List<String> options;
        
        @Getter
        @Setter
        private List<String> correct_answers;

    }
}
