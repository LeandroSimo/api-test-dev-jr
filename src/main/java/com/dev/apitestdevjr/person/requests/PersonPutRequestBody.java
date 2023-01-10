package com.dev.apitestdevjr.person.requests;


import com.dev.apitestdevjr.person.dtos.PersonDtoFull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonPutRequestBody extends PersonDtoFull {
    private Long id;
    private String name;
}
