package com.dev.apitestdevjr.person.controller;

import com.dev.apitestdevjr.exception.model.Response;
import com.dev.apitestdevjr.person.dtos.PersonDtoFull;
import com.dev.apitestdevjr.person.services.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/person")
public class PersonController {

    public static final String ID = "/{id}";

    private final PersonService personService;

    //Lista todas as pessoas com paginação
    @GetMapping
    public Response<Page<PersonDtoFull>> findALlPerson(Pageable pageable) {
        Response<Page<PersonDtoFull>> response = new Response<>();
        response.setData(personService.findALlPerson(pageable));
        response.setStatusCode(HttpStatus.OK.value());
        return response;
    }

    //Busca Pessoa por ID
    @GetMapping(value = ID)
    public ResponseEntity<Response<PersonDtoFull>> findPersonById(@PathVariable Long id) {
        Response<PersonDtoFull> response = new Response<>();
        response.setData(this.personService.findByIdPersonDto(id));
        response.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Salva Pessoa
    @PostMapping(value = "/register")
    public ResponseEntity<Response<Boolean>> createUser(@RequestBody PersonDtoFull person) {
        Response<Boolean> response = new Response<>();
        response.setData(this.personService.savePerson(person));
        response.setStatusCode(HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Atualiza Pessoa
    @PutMapping
    public ResponseEntity<Response <Boolean>> updateUser(@Valid @RequestBody PersonDtoFull person) {
        Response<Boolean> response = new Response<>();
        response.setData(this.personService.updatePerson(person));
        response.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Deleta pessoa pelo ID
    @DeleteMapping(value = ID)
    public ResponseEntity<Response<Boolean>> deletePerson(@PathVariable Long id) {
        Response<Boolean> response = new Response<>();
        response.setData(this.personService.deletePerson(id));
        response.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
