package net.planetjones.quiz_hoster.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import net.planetjones.quiz_hoster.domain.QuestionType;
import net.planetjones.quiz_hoster.domain.QuizAnswer;
import net.planetjones.quiz_hoster.domain.QuizQuestion;
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
      QuizQuestion question = new QuizQuestion(questionId, 1L, QuestionType.MULTIPLE_CHOICE,
          "What is the capital of France?");
      quizService.sendQuestion("/topic/quiz/", question);
    }
  }

}