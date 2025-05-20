package org.example.controller;
import org.example.model.Survey;
import org.example.service.SurveyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping
    public List<Survey> getAllSurveys(@RequestHeader("x-api-key") String apiKey) {
        return surveyService.getAll(apiKey);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable Long id, @RequestHeader("x-api-key") String apiKey) {
        return surveyService.getById(id, apiKey)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Survey createSurvey(@RequestBody Survey survey, @RequestHeader("x-api-key") String apiKey) {
        return surveyService.createSurvey(survey, apiKey);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Survey> updateSurvey(@PathVariable Long id, @RequestBody Survey survey, @RequestHeader("x-api-key") String apiKey) {
        return surveyService.updateSurvey(id, survey, apiKey)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long id, @RequestHeader("x-api-key") String apiKey) {
        surveyService.deleteSurvey(id, apiKey);
        return ResponseEntity.noContent().build();
    }
}
