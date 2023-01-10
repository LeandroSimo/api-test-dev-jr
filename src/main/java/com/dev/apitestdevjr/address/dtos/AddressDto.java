package com.dev.apitestdevjr.address.dtos;

import com.dev.apitestdevjr.address.enums.TypeAddress;
import com.dev.apitestdevjr.address.model.Address;
import lombok.*;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AddressDto {


    private Long id;
    private String street;
    private Integer zipCode;
    private Integer addressNumber;
    private String city;
    private TypeAddress typeAddress;

    private Long person;
    public AddressDto(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.zipCode = address.getZipCode();
        this.addressNumber = address.getAddressNumber();
        this.city = address.getCity();
        this.typeAddress = TypeAddress.SIM;
        this.person = address.getPerson().getId();
    }
}
