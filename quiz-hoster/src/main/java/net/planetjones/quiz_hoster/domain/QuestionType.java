package net.planetjones.quiz_hoster.domain;

public enum QuestionType {

    MULTIPLE_CHOICE("Multiple Choice"),
    TRUE_FALSE("True/False"),
    SHORT_ANSWER("Short Answer");

    private String displayName;

    QuestionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
