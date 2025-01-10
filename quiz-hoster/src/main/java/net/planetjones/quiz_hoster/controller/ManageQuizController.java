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
public class ManageQuizController {

  private static final Logger logger = LoggerFactory.getLogger(ManageQuizController.class);

  private final QuizService quizService;

  @Autowired
  public ManageQuizController(QuizService quizService) {
    this.quizService = quizService;
  }

  @MessageMapping("/beginQuiz")
  public void beginQuiz(String aString) throws Exception {
    
  }

}