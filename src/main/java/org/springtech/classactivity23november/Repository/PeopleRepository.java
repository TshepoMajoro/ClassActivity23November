package org.springtech.classactivity23november.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springtech.classactivity23november.Entities.Person;

import java.util.Optional;

public interface PeopleRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
    boolean existsByEmail(String email);
    void deleteByEmail(String email);
}
