package com.dev.apitestdevjr.address.model;

import com.dev.apitestdevjr.address.enums.TypeAddress;
import com.dev.apitestdevjr.person.model.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "zipcode")
    private Integer zipCode;

    @Column(name = "addressnumber")
    private Integer addressNumber;

    @Column(name = "city")
    private String city;
    private TypeAddress typeAddress;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;


    public Address( String street, Integer zipCode, Integer addressNumber, String city, Person person) {
        this.street = street;
        this.zipCode = zipCode;
        this.addressNumber = addressNumber;
        this.city = city;
        this.typeAddress = TypeAddress.SIM;
        this.person = person;
    }

}


