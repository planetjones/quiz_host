package net.planetjones.quiz_hoster.domain.exception;

public class QuizSessionNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public QuizSessionNotFoundException(String message) {
        super(message);
    }

    public QuizSessionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
