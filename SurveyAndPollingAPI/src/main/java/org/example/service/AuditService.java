package org.example.service;
import org.example.model.AuditLog;
import org.example.repository.AuditLogRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void log(String operation, String entity, String apiKey) {
        AuditLog log = new AuditLog();
        log.setOperation(operation);
        log.setEntity(entity);
        log.setTimestamp(LocalDateTime.now());
        log.setApiKey(apiKey);
        auditLogRepository.save(log);
    }

    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAll();
    }
}
