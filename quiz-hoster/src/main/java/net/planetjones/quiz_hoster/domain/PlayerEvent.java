package net.planetjones.quiz_hoster.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class PlayerEvent {

    @Getter
    private String playerName;

    @Getter
    private String eventType;

    @Getter
    private Integer score;

    public PlayerEvent() {
    }

    public PlayerEvent(String playerName, String eventType, Integer score) {
        this.playerName = playerName;
        this.eventType = eventType;
        this.score = score;
    }


}
