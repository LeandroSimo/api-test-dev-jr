package com.dev.apitestdevjr.person.repositories;

import com.dev.apitestdevjr.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query(value = "SELECT p FROM Person p INNER JOIN Address a ON p.id = a.person.id WHERE a.typeAddress = 0 and p.id = :id")
    Person findPersonMainAddress(@Param("id")Long id);
}
