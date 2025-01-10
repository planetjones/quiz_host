package net.planetjones.quiz_hoster.domain;

public class QuizQuestion {

    private Long quizId;
    private Long questionId;
    private QuestionType type;
    private String question;
    private String answer;

    public QuizQuestion() {
    }

    public QuizQuestion(Long quizId, Long questionId, QuestionType type, String question) {
        this.quizId = quizId;
        this.questionId = questionId;
        this.type = type;
        this.question = question;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
