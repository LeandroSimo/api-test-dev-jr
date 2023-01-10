package com.dev.apitestdevjr.utils.person;

import com.dev.apitestdevjr.person.requests.PersonPutRequestBody;

public class PersonPutRequestBodyCreator {

    public static PersonPutRequestBody createPersonPutRequestBody(){
        return PersonPutRequestBody.builder()
                .id(PersonCreator.createValidUpdatePerson().getId())
                .name(PersonCreator.createValidUpdatePerson().getName())
                .build();
    }

}
