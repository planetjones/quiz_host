package net.planetjones.quiz_hoster.service;

import net.planetjones.quiz_hoster.QuizHosterApplication;
import net.planetjones.quiz_hoster.controller.ManageQuizController;
import net.planetjones.quiz_hoster.domain.Quiz;
import net.planetjones.quiz_hoster.service.QuizService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureWebTestClient
public class ManageQuizControllerTest {

    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {
        webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
    }

    @Test
    public void testFindAllQuizzes() {

        webTestClient.get().uri("/api/quiz/list")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Quiz.class)
                .hasSize(1);
    }
}