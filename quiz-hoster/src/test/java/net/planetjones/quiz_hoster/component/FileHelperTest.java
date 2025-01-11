package net.planetjones.quiz_hoster.component;

import net.planetjones.quiz_hoster.domain.Quiz;
import net.planetjones.quiz_hoster.domain.Quiz.Question;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileHelperTest {

    private FileHelper fileHelper;

    @BeforeEach
    public void setUp() {
        fileHelper = new FileHelper();
    }

    @Test
    public void testLoadQuizzes() throws Exception {
        Map<Long, Quiz> quizzes = fileHelper.loadQuizzes();
        assertTrue(quizzes.size() == 1, "Quizzes list should not be empty");

        Quiz sampleQuiz = quizzes.get(1L);

        System.out.println(sampleQuiz);

        assertEquals("This is a sample quiz", sampleQuiz.getDescription());
        assertEquals(3, sampleQuiz.getQuestions().size());

        Question question1 = sampleQuiz.getQuestions().get(0);
        assertEquals("What is the capital of France?", question1.getQuestion());
        assertEquals("single_answer", question1.getType());
        assertEquals(List.of("Berlin", "Madrid", "Paris", "Rome"), question1.getOptions());
        assertEquals(List.of("Paris"), question1.getCorrect_answers());

        Question question2 = sampleQuiz.getQuestions().get(1);
        assertEquals("Which of these are programming languages?", question2.getQuestion());
        assertEquals("multiple_answer", question2.getType());
        assertEquals(List.of("Java", "HTML", "Python", "Photoshop"), question2.getOptions());
        assertEquals(List.of("Java", "Python"), question2.getCorrect_answers());

        Question question3 = sampleQuiz.getQuestions().get(2);
        assertEquals("Is this a quiz?", question3.getQuestion());
        assertEquals("single_answer", question3.getType());
        assertEquals(List.of("Yes", "No"), question3.getOptions());
        assertEquals(List.of("Yes"), question3.getCorrect_answers());

    }
}