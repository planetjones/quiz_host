package net.planetjones.quiz_hoster.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.planetjones.quiz_hoster.domain.Quiz;
import net.planetjones.quiz_hoster.service.QuizService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FileHelper {

    private static final Logger logger = LoggerFactory.getLogger(FileHelper.class);

    private final PathMatchingResourcePatternResolver resolver;

    public FileHelper() {
        this.resolver = new PathMatchingResourcePatternResolver();
    }

    public List<Resource> findJsonFilesInDirectory(String directoryPath) throws IOException {

        List<Resource> jsonFiles = new ArrayList<>();
        Resource[] resources = resolver.getResources(directoryPath + "/**/*.json");

        for (Resource resource : resources) {
            jsonFiles.add(resource);
        }
        return jsonFiles;
    }

    public Map<Long, Quiz> loadQuizzes() throws Exception {

        Map<Long, Quiz> quizzes = new HashMap<>();

        this.findJsonFilesInDirectory("/quizzes").forEach(file -> {
            logger.info("Loading quiz from file: {}", file.getFilename());
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("quizzes/" + file.getFilename());          
            Quiz quiz;
            try {
                quiz = objectMapper.readValue(inputStream, Quiz.class);
                quizzes.put(quiz.getId(), quiz);
            } catch (IOException e) {
                logger.error("Error reading quiz from file: {}", file.getFilename(), e);
            }
            
        });

        logger.info("Loaded {} quizzes", quizzes.keySet());
        return quizzes;
    }
}