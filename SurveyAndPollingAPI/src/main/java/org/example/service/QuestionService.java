package org.example.service;
import org.example.model.Question;
import org.example.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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

    private String getApiKeyFromRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            return request.getHeader("x-api-key");
        }
        return "unknown";
    }

    public List<Question> getAll() {
        auditService.log("READ_ALL", "Question", getApiKeyFromRequest());
        return questionRepository.findAll();
    }

    public Optional<Question> getById(Long id) {
        auditService.log("READ", "Question", getApiKeyFromRequest());
        return questionRepository.findById(id);
    }

    public Question createQuestion(Question question) {
        auditService.log("CREATE", "Question", getApiKeyFromRequest());
        return questionRepository.save(question);
    }

    public Optional<Question> updateQuestion(Long id, Question updatedQuestion) {
        auditService.log("UPDATE", "Question", getApiKeyFromRequest());
        return questionRepository.findById(id).map(existingQuestion -> {
            existingQuestion.setText(updatedQuestion.getText());
            existingQuestion.setSurvey(updatedQuestion.getSurvey());
            existingQuestion.setOptions(updatedQuestion.getOptions());
            return questionRepository.save(existingQuestion);
        });
    }

    public void deleteQuestion(Long id) {
        auditService.log("DELETE", "Question", getApiKeyFromRequest());
        questionRepository.deleteById(id);
    }
}
