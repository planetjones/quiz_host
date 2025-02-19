package net.planetjones.quiz_hoster.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import net.planetjones.quiz_hoster.component.FileHelper;
import net.planetjones.quiz_hoster.domain.Player;
import net.planetjones.quiz_hoster.domain.Quiz;
import net.planetjones.quiz_hoster.domain.QuizSession;
import net.planetjones.quiz_hoster.domain.exception.QuizSessionNotFoundException;

@Service
public class QuizService {

    private static final Logger logger = LoggerFactory.getLogger(QuizService.class);

    private final SimpMessagingTemplate messagingTemplate;
    private final Map<Long, Quiz> quizzes;
    private final ConcurrentHashMap<String, QuizSession> quizSession;
    private final FileHelper fileHelper;

    public QuizService(SimpMessagingTemplate messagingTemplate, FileHelper fileHelper) throws Exception {
        this.messagingTemplate = messagingTemplate;
        this.fileHelper = fileHelper;
        this.quizSession = new ConcurrentHashMap<>();
        this.quizzes = fileHelper.loadQuizzes();
    }

    public void sendQuestion(String topic, String question) {
        messagingTemplate.convertAndSend(topic, question);
    }

    public void registerPlayer(QuizSession session, String playerName) {
        session.addPlayer(new Player(playerName));
    }

    public QuizSession findQuizSession(String quizSessionId) {
        QuizSession session = quizSession.get(quizSessionId);

        if (session == null) {
            throw new QuizSessionNotFoundException("Quiz session not found: " + quizSessionId);
        }

        return session;
    }

    public QuizSession startQuiz(Long quizId) {
        Quiz quiz = quizzes.get(quizId);
        logger.info("Starting quiz: {}", quiz.getDescription());
        QuizSession session = new QuizSession(quiz);
        quizSession.put(session.getId(), session);
        return session;
    }

    public void beginQuiz(String sessionId) {
        QuizSession session = findQuizSession(sessionId);
       
    }

    public List<Quiz> findAllQuizzes() {
        return new ArrayList<>(quizzes.values());
    }

}