package org.example.controller;
import org.example.model.Option;
import org.example.service.OptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/options")
public class OptionController {

    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping
    public List<Option> getAllOptions(@RequestHeader("x-api-key") String apiKey) {
        return optionService.getAll(apiKey);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Option> getOptionById(@PathVariable Long id, @RequestHeader("x-api-key") String apiKey) {
        return optionService.getById(id, apiKey)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Option createOption(@RequestBody Option option, @RequestHeader("x-api-key") String apiKey) {
        return optionService.createOption(option, apiKey);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Option> updateOption(@PathVariable Long id, @RequestBody Option option, @RequestHeader("x-api-key") String apiKey) {
        return optionService.updateOption(id, option, apiKey)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOption(@PathVariable Long id, @RequestHeader("x-api-key") String apiKey) {
        optionService.deleteOption(id, apiKey);
        return ResponseEntity.noContent().build();
    }
}
