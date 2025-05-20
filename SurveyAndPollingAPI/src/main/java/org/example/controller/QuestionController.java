package org.example.controller;
import org.example.model.Question;
import org.example.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<Question> getAllQuestions(@RequestHeader("x-api-key") String apiKey) {
        return questionService.getAll(apiKey);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id, @RequestHeader("x-api-key") String apiKey) {
        return questionService.getById(id, apiKey)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Question createQuestion(@RequestBody Question question, @RequestHeader("x-api-key") String apiKey) {
        return questionService.createQuestion(question, apiKey);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question, @RequestHeader("x-api-key") String apiKey) {
        return questionService.updateQuestion(id, question, apiKey)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id, @RequestHeader("x-api-key") String apiKey) {
        questionService.deleteQuestion(id, apiKey);
        return ResponseEntity.noContent().build();
    }
}
