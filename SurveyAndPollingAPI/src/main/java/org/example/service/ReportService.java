package org.example.service;
import org.example.model.Response;
import org.example.repository.ResponceRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final ResponceRepository responseRepository;
    private final AuditService auditService;

    public ReportService(ResponceRepository responseRepository, AuditService auditService) {
        this.responseRepository = responseRepository;
        this.auditService = auditService;
    }

    public Map<String, Object> getSurveySummary() {
        List<Response> responses = responseRepository.findAll();
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalResponses", responses.size());
        auditService.log("REPORT", "SurveySummary", "system");
        return summary;
    }

    public Map<String, Object> getAuditLogs() {
        Map<String, Object> logs = new HashMap<>();
        logs.put("logs", auditService.getAllLogs());
        auditService.log("REPORT", "AuditLogs", "system");
        return logs;
    }
}
