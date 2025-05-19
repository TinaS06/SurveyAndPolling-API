package org.example.service;

import org.example.model.Response;
import org.example.repository.ResponceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseService {

    private final ResponceRepository responseRepository;

    public ResponseService(ResponceRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    public List<Response> getAllResponses() {
        return responseRepository.findAll();
    }

    public Response saveResponse(Response response) {
        return responseRepository.save(response);
    }

    // Get a response by ID
    public Optional<Response> getResponseById(Long id) {
        return responseRepository.findById(id);
    }

    // Delete a response by ID
    public void deleteResponse(Long id) {
        responseRepository.deleteById(id);
    }
}
