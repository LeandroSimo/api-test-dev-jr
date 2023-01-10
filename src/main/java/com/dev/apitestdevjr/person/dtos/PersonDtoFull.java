package com.dev.apitestdevjr.person.dtos;

import com.dev.apitestdevjr.address.model.Address;
import com.dev.apitestdevjr.person.model.Person;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class PersonDtoFull {

    private Long id;
    @NotNull
    @NotEmpty(message = "Name field cannot be empty or null")
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    List<Address> addressList = new ArrayList<>();

    public PersonDtoFull(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.birthDate = person.getBirthDate();
        this.addressList = person.getAddressList();
    }
}
