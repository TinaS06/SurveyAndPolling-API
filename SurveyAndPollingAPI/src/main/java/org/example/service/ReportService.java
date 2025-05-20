package org.example.service;
import org.example.model.Response;
import org.example.repository.ResponseRepository;
import org.springframework.stereotype.Service;
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

    public Map<String, Object> getSurveySummary(String apiKey) {
        List<Response> responses = responseRepository.findAll();
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalResponses", responses.size());
        auditService.log("REPORT", "SurveySummary", apiKey);
        return summary;
    }

    public Map<String, Object> getAuditLogs(String apiKey) {
        Map<String, Object> logs = new HashMap<>();
        logs.put("logs", auditService.getAllLogs());
        auditService.log("REPORT", "AuditLogs", apiKey);
        return logs;
    }
}