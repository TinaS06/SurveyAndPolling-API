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
    public List<Response> getAllResponses() {
        return responseService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getResponseById(@PathVariable Long id) {
        return responseService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Response createResponse(@RequestBody Response response) {
        return responseService.createResponse(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateResponse(@PathVariable Long id, @RequestBody Response updatedResponse) {
        return responseService.updateResponse(id, updatedResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable Long id) {
        responseService.deleteResponse(id);
        return ResponseEntity.noContent().build();
    }
}
