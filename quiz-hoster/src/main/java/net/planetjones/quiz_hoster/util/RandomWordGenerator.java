package net.planetjones.quiz_hoster.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomWordGenerator {

    private static final Set<String> generatedWords = new HashSet<>();

    private static final String[] ADJECTIVES = {
            "happy", "sad", "angry", "calm", "bright", "dark", "fast", "slow", "large", "small",
            "red", "blue", "green", "yellow", "purple", "orange", "pink", "gray", "black", "white",
            "clever", "foolish", "brave", "cowardly", "strong", "weak", "rich", "poor", "kind", "cruel"
    };

    private static final String[] NOUNS = {
            "dog", "cat", "bird", "tree", "house", "car", "book", "computer", "phone", "table",
            "sun", "moon", "star", "cloud", "rain", "snow", "wind", "fire", "water", "earth",
            "time", "life", "world", "way", "year", "day", "night", "people", "man", "woman"
    };

    private static final String[] VERBS = {
            "run", "jump", "walk", "fly", "eat", "sleep", "think", "dream", "write", "read",
            "speak", "listen", "see", "hear", "feel", "touch", "love", "hate", "learn", "teach",
            "create", "destroy", "build", "break", "fix", "move", "stop", "begin", "end", "continue"
    };


    public static String generateUniqueThreeWords() {
        Random random = new Random();

        while (generatedWords.size() < 3) {
            String word = null;
            int wordListIndex = random.nextInt(3); // 0: adjective, 1: noun, 2: verb
            switch (wordListIndex) {
                case 0:
                    word = ADJECTIVES[random.nextInt(ADJECTIVES.length)];
                    break;
                case 1:
                    word = NOUNS[random.nextInt(NOUNS.length)];
                    break;
                case 2:
                    word = VERBS[random.nextInt(VERBS.length)];
                    break;
            }

            generatedWords.add(word);
        }

        return String.join("-", generatedWords);
    }

}