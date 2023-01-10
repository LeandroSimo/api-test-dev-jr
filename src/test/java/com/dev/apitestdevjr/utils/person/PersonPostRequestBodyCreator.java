package com.dev.apitestdevjr.utils.person;

import com.dev.apitestdevjr.person.requests.PersonPostRequestBody;

public class PersonPostRequestBodyCreator {

    public static PersonPostRequestBody createPersonPostRequestBody(){
        return PersonPostRequestBody.builder()
                .name(PersonCreator.createPersonToBeSaved().getName())
                .build();
    }
}
