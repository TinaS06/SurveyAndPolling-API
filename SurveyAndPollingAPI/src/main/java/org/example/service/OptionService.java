package org.example.service;
import org.example.model.Option;
import org.example.repository.OptionRepository;
import org.springframework.stereotype.Service;
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

    public List<Option> getAll(String apiKey) {
        auditService.log("READ_ALL", "Option", apiKey);
        return optionRepository.findAll();
    }

    public Optional<Option> getById(Long id, String apiKey) {
        auditService.log("READ", "Option", apiKey);
        return optionRepository.findById(id);
    }

    public Option createOption(Option option, String apiKey) {
        auditService.log("CREATE", "Option", apiKey);
        return optionRepository.save(option);
    }

    public Optional<Option> updateOption(Long id, Option updatedOption, String apiKey) {
        auditService.log("UPDATE", "Option", apiKey);
        return optionRepository.findById(id).map(existingOption -> {
            existingOption.setText(updatedOption.getText());
            existingOption.setQuestion(updatedOption.getQuestion());
            return optionRepository.save(existingOption);
        });
    }

    public void deleteOption(Long id, String apiKey) {
        auditService.log("DELETE", "Option", apiKey);
        optionRepository.deleteById(id);
    }
}
