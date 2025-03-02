package net.planetjones.quiz_hoster.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import net.planetjones.quiz_hoster.domain.PlayerEvent;
import net.planetjones.quiz_hoster.domain.QuizRegistration;
import net.planetjones.quiz_hoster.service.QuizService;

@CrossOrigin(origins = "*")
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

  @PostMapping("/api/player/registerForQuiz")
  public ResponseEntity<Map<String, String>> register(@RequestBody QuizRegistration registration) throws Exception {
    logger.info("Player: {} registered for quiz: {}", registration.getPlayerName(), registration.getQuizSessionId());
    quizService.registerPlayer(quizService.findQuizSession(registration.getQuizSessionId()),
        registration.getPlayerName());

    String playerTopic = String.format("/topic/players/%s/%s", registration.getQuizSessionId(), registration.getPlayerName());
    messagingTemplate.convertAndSend(playerTopic, "PLAYER_REGISTERED");

    String quizSessionTopic = String.format("/topic/%s", registration.getQuizSessionId());
    messagingTemplate.convertAndSend(quizSessionTopic, new PlayerEvent(registration.getPlayerName(), "PLAYER_REGISTERED", 0));

    Map<String, String> response = new HashMap<>();
    response.put("status", "PLAYER_REGISTERED");
    return ResponseEntity.ok(response);
  }

}