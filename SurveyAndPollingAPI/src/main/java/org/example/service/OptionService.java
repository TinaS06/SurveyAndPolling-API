package org.example.service;
import org.example.model.Option;
import org.example.repository.OptionRepository;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.List;
import java.util.Optional;

@Service
public class OptionService {

    private final OptionRepository optionRepository;
    private final AuditService auditService;

    public OptionService(OptionRepository optionRepository, AuditService auditService) {
        this.optionRepository = optionRepository;
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

    public List<Option> getAll() {
        auditService.log("READ_ALL", "Option", getApiKeyFromRequest());
        return optionRepository.findAll();
    }

    public Optional<Option> getById(Long id) {
        auditService.log("READ", "Option", getApiKeyFromRequest());
        return optionRepository.findById(id);
    }

    public Option createOption(Option option) {
        auditService.log("CREATE", "Option", getApiKeyFromRequest());
        return optionRepository.save(option);
    }

    public Optional<Option> updateOption(Long id, Option updatedOption) {
        auditService.log("UPDATE", "Option", getApiKeyFromRequest());
        return optionRepository.findById(id).map(existingOption -> {
            existingOption.setText(updatedOption.getText());
            existingOption.setQuestion(updatedOption.getQuestion());
            return optionRepository.save(existingOption);
        });
    }

    public void deleteOption(Long id) {
        auditService.log("DELETE", "Option", getApiKeyFromRequest());
        optionRepository.deleteById(id);
    }
}