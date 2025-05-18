package org.example.service;
import org.example.model.Survey;
import org.example.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;
    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public Survey createSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }


    public List<Survey> getAll() {
        return surveyRepository.findAll();
    }


    public Optional<Survey> getById(Long id) {
        return surveyRepository.findById(id);
    }


    public Optional<Survey> updateSurvey(Long id, Survey updatedSurvey) {
        return surveyRepository.findById(id).map(existingSurvey -> {
            existingSurvey.setTitle(updatedSurvey.getTitle());
            existingSurvey.setQuestions(updatedSurvey.getQuestions());
            return surveyRepository.save(existingSurvey);
        });
    }


    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }

}
