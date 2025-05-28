package org.example.service;
import org.example.model.Response;
import org.example.repository.ResponseRepository;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final ResponseRepository responseRepository;
    private final AuditService auditService;

    public ReportService(ResponseRepository responseRepository, AuditService auditService) {
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

    public Map<String, Object> getSurveySummary() {
        List<Response> responses = responseRepository.findAll();
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalResponses", responses.size());
        auditService.log("REPORT", "SurveySummary", getApiKeyFromRequest());
        return summary;
    }

    public Map<String, Object> getAuditLogs() {
        Map<String, Object> logs = new HashMap<>();
        logs.put("logs", auditService.getAllLogs());
        auditService.log("REPORT", "AuditLogs", getApiKeyFromRequest());
        return logs;
    }
}
