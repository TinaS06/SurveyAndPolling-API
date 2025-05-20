package org.example.service;
import org.example.model.Response;
import org.example.repository.ResponseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ResponseService {

    private final ResponseRepository responseRepository;
    private final AuditService auditService;

    public ResponseService(ResponseRepository responseRepository, AuditService auditService) {
        this.responseRepository = responseRepository;
        this.auditService = auditService;
    }

    public List<Response> getAllResponses(String apiKey) {
        auditService.log("READ_ALL", "Response", apiKey);
        return responseRepository.findAll();
    }

    public Response saveResponse(Response response, String apiKey) {
        auditService.log("CREATE", "Response", apiKey);
        return responseRepository.save(response);
    }

    public Optional<Response> getResponseById(Long id, String apiKey) {
        auditService.log("READ", "Response", apiKey);
        return responseRepository.findById(id);
    }

    public void deleteResponse(Long id, String apiKey) {
        auditService.log("DELETE", "Response", apiKey);
        responseRepository.deleteById(id);
    }

    public Optional<Response> updateResponse(Long id, Response updatedResponse, String apiKey) {
        auditService.log("UPDATE", "Response", apiKey);
        return responseRepository.findById(id).map(existingResponse -> {
            existingResponse.setSurvey(updatedResponse.getSurvey());
            existingResponse.setQuestion(updatedResponse.getQuestion());
            existingResponse.setSelectedOption(updatedResponse.getSelectedOption());
            existingResponse.setRespondent(updatedResponse.getRespondent());
            return responseRepository.save(existingResponse);
        });
    }
}