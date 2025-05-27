package org.example.service;
import org.example.model.Survey;
import org.example.repository.SurveyRepository;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final AuditService auditService;

    public SurveyService(SurveyRepository surveyRepository, AuditService auditService) {
        this.surveyRepository = surveyRepository;
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

    public List<Survey> getAll() {
        auditService.log("READ_ALL", "Survey", getApiKeyFromRequest());
        return surveyRepository.findAll();
    }

    public Optional<Survey> getById(Long id) {
        auditService.log("READ", "Survey", getApiKeyFromRequest());
        return surveyRepository.findById(id);
    }

    public Survey createSurvey(Survey survey) {
        auditService.log("CREATE", "Survey", getApiKeyFromRequest());
        return surveyRepository.save(survey);
    }

    public Optional<Survey> updateSurvey(Long id, Survey updatedSurvey) {
        auditService.log("UPDATE", "Survey", getApiKeyFromRequest());
        return surveyRepository.findById(id).map(existingSurvey -> {
            existingSurvey.setTitle(updatedSurvey.getTitle());
            existingSurvey.setQuestions(updatedSurvey.getQuestions());
            return surveyRepository.save(existingSurvey);
        });
    }

    public void deleteSurvey(Long id) {
        auditService.log("DELETE", "Survey", getApiKeyFromRequest());
        surveyRepository.deleteById(id);
    }
}