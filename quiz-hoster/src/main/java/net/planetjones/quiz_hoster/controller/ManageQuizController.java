package net.planetjones.quiz_hoster.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import net.planetjones.quiz_hoster.domain.Quiz;
import net.planetjones.quiz_hoster.domain.QuizSession;
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

  @PostMapping("/api/quiz/begin")
  public QuizSession beginQuiz(@RequestBody Map<String, Long> payload) throws Exception {
    Long quizId = payload.get("quizId");
    logger.info("Requested to begin quiz id {}", quizId);
    QuizSession session = quizService.startQuiz(quizId);
    logger.info("Quiz session {} has begun", session.getId());
    return session;
  }

}