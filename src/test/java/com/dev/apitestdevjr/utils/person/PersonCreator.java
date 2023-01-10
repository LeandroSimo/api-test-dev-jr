package com.dev.apitestdevjr.utils.person;

import com.dev.apitestdevjr.person.model.Person;

public class PersonCreator {

    public static Person createPersonToBeSaved() {
        return Person.builder()
                .name("Leandro")
                .build();
    }

    public static Person createValidPerson() {
        return Person.builder()
                .id(1L)
                .name("Leandro")
                .build();
    }

    public static Person createValidUpdatePerson() {
        return Person.builder()
                .id(1L)
                .name("Leandro Simoes")
                .build();
    }
}

