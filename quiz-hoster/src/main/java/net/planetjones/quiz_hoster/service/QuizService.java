package net.planetjones.quiz_hoster.service;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import net.planetjones.quiz_hoster.domain.QuizQuestion;
import net.planetjones.quiz_hoster.domain.QuizSession;

@Service
public class QuizService {

    private final SimpMessagingTemplate messagingTemplate;
    private final HashMap<String, QuizSession> quizSession = new ConcurrentHashMap<>();

    public QuizService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendQuestion(String topic, QuizQuestion question) {
        messagingTemplate.convertAndSend(topic, question);
    }


}
