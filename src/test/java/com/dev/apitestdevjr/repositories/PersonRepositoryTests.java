package com.dev.apitestdevjr.repositories;

import com.dev.apitestdevjr.person.model.Person;
import com.dev.apitestdevjr.person.repositories.PersonRepository;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTests {
    @Autowired
    private PersonRepository personRepository;

    @Test
    @DisplayName(value = "Salvando pessoa no Bando de Dados")
    public void createShouldPersistData() {
        LocalDate localDate = LocalDate.now();
        Person person = new Person("Leandro", localDate);
        this.personRepository.save(person);
        assertThat(person.getId()).isNotNull();
        assertThat(person.getName())
                .isEqualTo("Leandro")
                .isNotNull()
                .isNotEmpty();
        assertThat(person.getBirthDate()).isNotNull();
    }

    @Test
    @DisplayName(value = "Deletando pessoa do Banco de Dados")
    public void deleteShouldRemoveData() {
        LocalDate localDate = LocalDate.now();
        Person person = new Person("Leandro", localDate);
        this.personRepository.save(person);
        this.personRepository.delete(person);
        assertThat(personRepository.findById(person.getId())).isNull();
    }

    @Test
    @DisplayName(value = "Atualizando dados da pessoa no Banco de Dados")
    public void updateShouldChangeAndPersistData() {
        LocalDate localDate = LocalDate.now();
        Person person = new Person("Leandro", localDate);
        this.personRepository.save(person);
        person.setName("Leandro Simoes");
        this.personRepository.save(person);
        assertThat(person.getId()).isNotNull();
        assertThat(person.getName())
                .isEqualTo("Leandro Simoes")
                .isNotEmpty()
                .isNotNull();
        assertThat(person.getBirthDate()).isNotNull();
        assertThat(person.getId()).isEqualTo(person.getId());
    }

    @Test
    @DisplayName(value = "Vericando se os atributos de pesssoa estão vazios")
    public void createThrowConstraintViolationExceptionWhenPersonIsEmpty(){
        Person person = new Person();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() ->this.personRepository.save(person))
                .withMessageContaining("The person cannot be empty");
    }
}
