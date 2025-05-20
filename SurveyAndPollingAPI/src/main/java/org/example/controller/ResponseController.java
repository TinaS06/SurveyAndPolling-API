package org.example.controller;
import org.example.model.Response;
import org.example.service.ResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/responses")
public class ResponseController {

    private final ResponseService responseService;

    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @GetMapping
    public List<Response> getAllResponses(@RequestHeader("x-api-key") String apiKey) {
        return responseService.getAllResponses(apiKey);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getResponseById(@PathVariable Long id, @RequestHeader("x-api-key") String apiKey) {
        return responseService.getResponseById(id, apiKey)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Response createResponse(@RequestBody Response response, @RequestHeader("x-api-key") String apiKey) {
        return responseService.saveResponse(response, apiKey);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateResponse(@PathVariable Long id, @RequestBody Response updatedResponse, @RequestHeader("x-api-key") String apiKey) {
        return responseService.updateResponse(id, updatedResponse, apiKey)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable Long id, @RequestHeader("x-api-key") String apiKey) {
        responseService.deleteResponse(id, apiKey);
        return ResponseEntity.noContent().build();
    }
}