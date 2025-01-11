package net.planetjones.quiz_hoster.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import net.planetjones.quiz_hoster.service.QuizService;

@Controller
public class PlayQuizController {

  private static final Logger logger = LoggerFactory.getLogger(PlayQuizController.class);

  private final QuizService quizService;

  @Autowired
  public PlayQuizController(QuizService quizService) {
    this.quizService = quizService;
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