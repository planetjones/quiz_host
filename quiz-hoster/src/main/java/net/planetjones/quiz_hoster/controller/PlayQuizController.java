package net.planetjones.quiz_hoster.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

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

  public String findQuizSession(String quizSessionId){
    logger.info("Finding quiz session: {}", quizSessionId);
    return "success";
  }

  @MessageMapping("/registerForQuiz")
  public void register(QuizRegistration registration) throws Exception {
    logger.info("Player: {} registered for quiz: {}", registration.getPlayerName(), registration.getQuizSessionId());
    quizService.registerPlayer(quizService.findQuizSession(registration.getQuizSessionId()), registration.getPlayerName());
    messagingTemplate.convertAndSend("/topic/players/" + registration.getPlayerName(), "PLAYER_REGISTERED");
  }


  @MessageMapping("/startQuiz")
  public void findNextQuestion(String aString) throws Exception {
    Thread.sleep(1000); // simulated delay
    logger.info("Received message: " + aString);

    for (int i = 0; i < 30; i++) {
      Thread.sleep(1000); // wait for 1 second
      long questionId = 1L + i;
     
      quizService.sendQuestion("/topic/quiz/", null);
    }
  }

}