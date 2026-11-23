package org.springtech.classactivity23november.Services;

import org.springframework.stereotype.Service;
import org.springtech.classactivity23november.DTOs.PersonRequest;
import org.springtech.classactivity23november.DTOs.PersonResponse;
import org.springtech.classactivity23november.Entities.Person;
import org.springtech.classactivity23november.Exceptions.ResourceNotFoundException;
import org.springtech.classactivity23november.Repository.PeopleRepository;

import java.util.List;

@Service
public class PersonService {
    private final PeopleRepository repository;

    public PersonService(PeopleRepository _repository){
        repository = _repository;
    }

    private PersonResponse MapToResponse(Person entity){
        return new PersonResponse(
                entity.getId(),
                entity.getName(),
                entity.getEmail()
        );
    }

    private Person MapToEntity(PersonRequest request){
        Person entity = new Person();
        entity.setEmail(request.email());
        entity.setName(request.name());
        return entity;
    }

    public List<PersonResponse> getAllPeople(){
        return repository.findAll().stream().map(person -> MapToResponse(person)).toList();
    }

    public PersonResponse getPersonById(long id) throws ResourceNotFoundException {
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Person resource with Id "+id+" does not exist.");
        }
        Person person = repository.findById(id).get();
        return MapToResponse(person);
    }

    public PersonResponse getPersonByEmail(String email) throws ResourceNotFoundException {
        if(!repository.existsByEmail(email)){
            throw new ResourceNotFoundException("Person resource with Email "+email+" does not exist.");
        }
        Person person = repository.findByEmail(email).get();
        return MapToResponse(person);
    }

    public void updatePerson(long id, PersonRequest updatedEntity) throws ResourceNotFoundException {
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Person resource with Id "+id+" does not exist.");
        }
        Person person = repository.findById(id).get();
        person.setEmail(updatedEntity.email());
        person.setName(updatedEntity.name());
        repository.save(person);
    }

    public void createPerson(PersonRequest newEntity) throws ResourceNotFoundException {
        if(repository.existsByEmail(newEntity.email())){
            throw new ResourceNotFoundException("Person resource with email "+newEntity.email()+" does already exists.");
        }
        repository.save(MapToEntity(newEntity));
    }

    public void deletePersonById(long id) throws ResourceNotFoundException {
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Person resource with Id "+id+" does not exist.");
        }
        repository.deleteById(id);
    }

    public void deletePersonByEmail(String email) throws ResourceNotFoundException {
        if(!repository.existsByEmail(email)){
            throw new ResourceNotFoundException("Person resource with email "+email+" does not exist.");
        }
        repository.deleteByEmail(email);
    }
}
