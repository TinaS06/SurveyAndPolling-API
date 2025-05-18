package org.example.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        // Example: returns aggregate data, counts, etc.
        return reportService.getSurveySummary();
    }

    @GetMapping("/audit-logs")
    public Map<String, Object> getAuditLogs() {
        return reportService.getAuditLogs();
    }

    //PROBLEM HERE LEFT OFF HERE NOT WORKING HELP
}
