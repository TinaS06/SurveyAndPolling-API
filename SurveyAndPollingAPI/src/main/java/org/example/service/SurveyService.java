package org.example.service;
import org.example.model.Survey;
import org.example.repository.SurveyRepository;
import org.springframework.stereotype.Service;
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

    public List<Survey> getAll(String apiKey) {
        auditService.log("GET_ALL", "Survey", apiKey);
        return surveyRepository.findAll();
    }

    public Optional<Survey> getById(Long id, String apiKey) {
        auditService.log("GET_BY_ID", "Survey", apiKey);
        return surveyRepository.findById(id);
    }

    public Survey createSurvey(Survey survey, String apiKey) {
        auditService.log("CREATE", "Survey", apiKey);
        return surveyRepository.save(survey);
    }

    public Optional<Survey> updateSurvey(Long id, Survey updatedSurvey, String apiKey) {
        auditService.log("UPDATE", "Survey", apiKey);
        return surveyRepository.findById(id).map(existingSurvey -> {
            existingSurvey.setTitle(updatedSurvey.getTitle());
            existingSurvey.setQuestions(updatedSurvey.getQuestions());
            return surveyRepository.save(existingSurvey);
        });
    }

    public void deleteSurvey(Long id, String apiKey) {
        auditService.log("DELETE", "Survey", apiKey);
        surveyRepository.deleteById(id);
    }
}
