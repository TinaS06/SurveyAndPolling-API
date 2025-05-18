package org.example.service;

import org.example.model.Question;
import org.example.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AuditService auditService;

    public QuestionService(QuestionRepository questionRepository, AuditService auditService) {
        this.questionRepository = questionRepository;
        this.auditService = auditService;
    }

    public List<Question> getAll(String apiKey) {
        auditService.log("GET_ALL", "Question", apiKey);
        return questionRepository.findAll();
    }

    public Optional<Question> getById(Long id, String apiKey) {
        auditService.log("GET_BY_ID", "Question", apiKey);
        return questionRepository.findById(id);
    }

    public Question createQuestion(Question question, String apiKey) {
        auditService.log("CREATE", "Question", apiKey);
        return questionRepository.save(question);
    }

    public Optional<Question> updateQuestion(Long id, Question updatedQuestion, String apiKey) {
        auditService.log("UPDATE", "Question", apiKey);
        return questionRepository.findById(id).map(existingQuestion -> {
            existingQuestion.setText(updatedQuestion.getText());
            existingQuestion.setSurvey(updatedQuestion.getSurvey());
            existingQuestion.setOptions(updatedQuestion.getOptions());
            return questionRepository.save(existingQuestion);
        });
    }

    public void deleteQuestion(Long id, String apiKey) {
        auditService.log("DELETE", "Question", apiKey);
        questionRepository.deleteById(id);
    }
}
