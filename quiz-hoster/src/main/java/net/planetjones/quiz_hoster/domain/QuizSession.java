package net.planetjones.quiz_hoster.domain;

import java.util.HashMap;
import java.util.Set;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import net.planetjones.quiz_hoster.util.RandomWordGenerator;

@EqualsAndHashCode
@ToString
public class QuizSession {

    @Getter
    private String id;

    @Getter
    private Quiz quiz;

    @Getter
    private int currentQuestionIndex;

    private HashMap<String, Player> players;

    public QuizSession(Quiz quiz) {
        this.id = RandomWordGenerator.generateUniqueThreeWords();
        this.quiz = quiz;
        this.currentQuestionIndex = 0;
        this.players = new HashMap<>();
    }

    public Player findPlayer(String playerName) {
        return players.get(playerName);
    }

    public void addPlayer(Player player) {
        players.put(player.getName(), player);
    }

}
