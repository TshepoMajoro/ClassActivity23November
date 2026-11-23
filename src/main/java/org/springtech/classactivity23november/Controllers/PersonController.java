package org.springtech.classactivity23november.Controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springtech.classactivity23november.DTOs.PersonRequest;
import org.springtech.classactivity23november.DTOs.PersonResponse;
import org.springtech.classactivity23november.Exceptions.ResourceNotFoundException;
import org.springtech.classactivity23november.Services.PersonService;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PersonController {
    private final PersonService service;

    public PersonController(PersonService _service){
        service = _service;
    }

    @GetMapping
    public ResponseEntity<List<PersonResponse>> getAll(){
        return ResponseEntity.ok(service.getAllPeople());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> getPerson(@PathVariable long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.getPersonById(id));
    }

    @GetMapping(params = "email")
    public ResponseEntity<PersonResponse> getPersonByEmail(@RequestParam String email) throws ResourceNotFoundException {
        return ResponseEntity.ok(service.getPersonByEmail(email));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePerson(@PathVariable long id, @Valid @RequestBody PersonRequest request) throws ResourceNotFoundException {
        service.updatePerson(id, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> createPerson(@Valid @RequestBody PersonRequest request) throws ResourceNotFoundException {
        service.createPerson(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable long id) throws ResourceNotFoundException {
        service.deletePersonById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(params = "email")
    public ResponseEntity<Void> deletePersonByEmail(@PathVariable String email) throws ResourceNotFoundException {
        service.deletePersonByEmail(email);
        return ResponseEntity.noContent().build();
    }
}
