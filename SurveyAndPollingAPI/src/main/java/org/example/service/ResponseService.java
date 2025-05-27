package org.example.service;
import org.example.model.Response;
import org.example.repository.ResponseRepository;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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

    private String getApiKeyFromRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            return request.getHeader("x-api-key");
        }
        return "unknown";
    }

    public List<Response> getAllResponses() {
        auditService.log("READ_ALL", "Response", getApiKeyFromRequest());
        return responseRepository.findAll();
    }

    public Response saveResponse(Response response) {
        auditService.log("CREATE", "Response", getApiKeyFromRequest());
        return responseRepository.save(response);
    }

    public Optional<Response> getResponseById(Long id) {
        auditService.log("READ", "Response", getApiKeyFromRequest());
        return responseRepository.findById(id);
    }

    public void deleteResponse(Long id) {
        auditService.log("DELETE", "Response", getApiKeyFromRequest());
        responseRepository.deleteById(id);
    }

    public Optional<Response> updateResponse(Long id, Response updatedResponse) {
        auditService.log("UPDATE", "Response", getApiKeyFromRequest());
        return responseRepository.findById(id).map(existingResponse -> {
            existingResponse.setSurvey(updatedResponse.getSurvey());
            existingResponse.setQuestion(updatedResponse.getQuestion());
            existingResponse.setSelectedOption(updatedResponse.getSelectedOption());
            existingResponse.setRespondent(updatedResponse.getRespondent());
            return responseRepository.save(existingResponse);
        });
    }
}