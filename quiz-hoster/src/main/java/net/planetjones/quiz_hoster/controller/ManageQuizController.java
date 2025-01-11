package net.planetjones.quiz_hoster.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.planetjones.quiz_hoster.domain.Quiz;
import net.planetjones.quiz_hoster.service.QuizService;

@RestController
public class ManageQuizController {

  private static final Logger logger = LoggerFactory.getLogger(ManageQuizController.class);

  private final QuizService quizService;

  @Autowired
  public ManageQuizController(QuizService quizService) {
    this.quizService = quizService;
  }

  @GetMapping("/api/quiz/list")
  public List<Quiz> findAllQuizzes() {
    return quizService.findAllQuizzes();
  }

  @MessageMapping("/beginQuiz")
  public void beginQuiz(Long quizId) throws Exception {
    logger.info("Requested to begin quiz id {}", quizId);
    quizService.startQuiz(quizId);
  }

}