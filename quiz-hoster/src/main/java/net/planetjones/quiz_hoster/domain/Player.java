package net.planetjones.quiz_hoster.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Player {
    
    @Getter
    private String name;
    
    @Getter
    private int score;
    
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public void addToScore(int amount) {
        score += amount;
    }

}