package com.dev.apitestdevjr.person.services;

import com.dev.apitestdevjr.person.PersonException.PersonException;
import com.dev.apitestdevjr.person.dtos.PersonDtoFull;
import com.dev.apitestdevjr.person.model.Person;
import com.dev.apitestdevjr.person.repositories.PersonRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PersonService {

    private static final  String MENSAGEM_ERROR= "Error interno identificado";
    private static final String USER_NAO_ENCONTRADO ="Usuário não encontrado";
    private final PersonRepository personRepository;

    public Page<PersonDtoFull> findALlPerson(Pageable pageable) {
        try {
            Page<Person> person = personRepository.findAll(pageable);
            return person.map(PersonDtoFull::new);
        } catch (Exception e) {
            throw new PersonException(MENSAGEM_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public Person findByIdPerson(Long id) {
        try {
            Optional<Person> personOptional = this.personRepository.findById(id);
            if (personOptional.isPresent()) {
                return this.personRepository.findById(id).get();
            }
            throw new PersonException(USER_NAO_ENCONTRADO, HttpStatus.NOT_FOUND);
        } catch (PersonException m) {
            throw m;
        } catch (Exception e) {
            throw new PersonException(MENSAGEM_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public PersonDtoFull findByIdPersonDto(Long id) {
        try {
            Optional<Person> userOptional = this.personRepository.findById(id);
            if (userOptional.isPresent()) {
                Person person = this.personRepository.findById(id).get();
                return new PersonDtoFull(person);
            }
            throw new PersonException(USER_NAO_ENCONTRADO, HttpStatus.NOT_FOUND);
        } catch (PersonException m) {
            throw m;
        } catch (Exception e) {
            throw new PersonException(MENSAGEM_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Boolean deletePerson(Long id) {
        try {
            this.findByIdPerson(id);
            this.personRepository.deleteById(id);
            return true;
        } catch (PersonException m) {
            throw m;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean savePerson(PersonDtoFull personDtoFull) {
        try {
            this.fromPerson(personDtoFull);
            return true;
        }catch (DataIntegrityViolationException m) {
            throw new PersonException(USER_NAO_ENCONTRADO, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            throw new PersonException(MENSAGEM_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Boolean updatePerson(PersonDtoFull personDtoFull) {
        try {
            findByIdPerson(personDtoFull.getId());
            this.fromPerson(personDtoFull);
            return true;
        } catch (PersonException m) {
            throw m;
        } catch (Exception e) {
            throw e;
        }
    }


    private Person fromPerson(PersonDtoFull obj) {
        Person newObj = new Person();
        newObj.setId(obj.getId());
        newObj.setName(obj.getName());
        newObj.setBirthDate(obj.getBirthDate());
        return personRepository.save(newObj);
    }
}
