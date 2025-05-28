package org.example.config;
import org.example.model.Option;
import org.example.model.Question;
import org.example.model.Survey;
import org.example.repository.OptionRepository;
import org.example.repository.QuestionRepository;
import org.example.repository.SurveyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

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

    @Override
    public void run(String... args) throws Exception {

        Survey survey = new Survey();
        survey.setTitle("Favorite Programming Language");
        survey = surveyRepository.save(survey);

        Question question = new Question();
        question.setText("Which programming language is your favorite?");
        question.setSurvey(survey);
        question = questionRepository.save(question);

        if (question.getOptions() == null) {
            question.setOptions(new ArrayList<>());
        }

        Option option1 = new Option("Java", question);
        Option option2 = new Option("Python", question);
        Option option3 = new Option("C++", question);

        List<Option> savedOptions = optionRepository.saveAll(List.of(option1, option2, option3));

        question.getOptions().addAll(savedOptions);
        questionRepository.save(question);


        if (survey.getQuestions() == null) {
            survey.setQuestions(new ArrayList<>());
        }
        survey.getQuestions().add(question);
        surveyRepository.save(survey);
    }
}
