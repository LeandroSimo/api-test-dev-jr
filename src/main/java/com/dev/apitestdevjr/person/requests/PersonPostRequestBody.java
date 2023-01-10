package com.dev.apitestdevjr.person.requests;


import com.dev.apitestdevjr.person.dtos.PersonDtoFull;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonPostRequestBody extends PersonDtoFull{

    @NotEmpty(message = "The anime name cannot be empty")
    private String name;
}
