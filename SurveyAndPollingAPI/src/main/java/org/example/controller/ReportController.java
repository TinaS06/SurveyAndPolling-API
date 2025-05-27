package org.example.controller;
import org.example.service.ReportService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/survey-summary")
    public Map<String, Object> getSurveySummary() {
        return reportService.getSurveySummary();
    }

    @GetMapping("/audit-logs")
    public Map<String, Object> getAuditLogs() {
        return reportService.getAuditLogs();
    }
}