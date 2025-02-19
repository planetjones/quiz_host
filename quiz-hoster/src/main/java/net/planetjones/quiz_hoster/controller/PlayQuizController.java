package net.planetjones.quiz_hoster.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import net.planetjones.quiz_hoster.domain.PlayerEvent;
import net.planetjones.quiz_hoster.domain.QuizRegistration;
import net.planetjones.quiz_hoster.service.QuizService;

@Controller
public class PlayQuizController {

  private static final Logger logger = LoggerFactory.getLogger(PlayQuizController.class);

  private final SimpMessagingTemplate messagingTemplate;
  private final QuizService quizService;

  @Autowired
  public PlayQuizController(QuizService quizService, SimpMessagingTemplate messagingTemplate) {
    this.quizService = quizService;
    this.messagingTemplate = messagingTemplate;
  }

  public String findQuizSession(String quizSessionId) {
    logger.info("Finding quiz session: {}", quizSessionId);
    return "success";
  }

  @MessageMapping("/registerForQuiz")
  public void register(QuizRegistration registration) throws Exception {
    logger.info("Player: {} registered for quiz: {}", registration.getPlayerName(), registration.getQuizSessionId());
    quizService.registerPlayer(quizService.findQuizSession(registration.getQuizSessionId()),
        registration.getPlayerName());

    String playerTopic = String.format("/topic/players/%s/%s", registration.getQuizSessionId(), registration.getPlayerName());
    messagingTemplate.convertAndSend(playerTopic, "PLAYER_REGISTERED");

    String quizSessionTopic = String.format("/topic/%s", registration.getQuizSessionId());
    messagingTemplate.convertAndSend(quizSessionTopic, new PlayerEvent(registration.getPlayerName(), "PLAYER_REGISTERED", 0));
  }

}