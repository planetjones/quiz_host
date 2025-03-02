package net.planetjones.quiz_hoster.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import net.planetjones.quiz_hoster.domain.PlayerEvent;
import net.planetjones.quiz_hoster.domain.Quiz;
import net.planetjones.quiz_hoster.domain.QuizRegistration;
import net.planetjones.quiz_hoster.domain.QuizSession;
import net.planetjones.quiz_hoster.service.QuizService;

@CrossOrigin(origins = "*")
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

  @MessageMapping("/startQuiz")
  public void startQuiz(String quizSessionId) throws Exception {
    logger.info("Starting quiz {}", quizSessionId);
  }

  @GetMapping(value = "/api/quiz/image", produces = MediaType.IMAGE_PNG_VALUE)
  public byte[] getQuizImage(@RequestParam("quizUrl") String quizUrl) throws Exception {
   logger.info("Requested image for quiz url {}", quizUrl);

    int width = 300;
    int height = 300;

    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix bitMatrix = qrCodeWriter.encode(quizUrl, BarcodeFormat.QR_CODE, width, height);
    BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ImageIO.write(bufferedImage, "PNG", outputStream);

    return outputStream.toByteArray();
  }

}