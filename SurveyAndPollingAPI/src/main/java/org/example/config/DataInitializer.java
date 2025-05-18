package org.example.config;
import org.example.repository.OptionRepository;
import org.example.repository.QuestionRepository;
import org.example.repository.SurveyRepository;

public class DataInitializer {

    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;

    public DataInitializer(SurveyRepository surveyRepository,
                           QuestionRepository questionRepository,
                           OptionRepository optionRepository) {
        this.surveyRepository = surveyRepository;
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
    }

}
