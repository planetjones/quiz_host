package net.planetjones.quiz_hoster.domain;

public class QuizAnswer {

    private String answer;
    private Long quizId;
    private Long questionId;

    public QuizAnswer() {
    }

    public QuizAnswer(String answer, Long quizId, Long questionId) {
        this.answer = answer;
        this.quizId = quizId;
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

}
