package com.dev.apitestdevjr.person.model;

import com.dev.apitestdevjr.address.model.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class Person {
    public Person( String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name field cannot be empty or null")
    @Column(name = "name")
    private String name;

    @Column(name = "birthdate")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Address> addressList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Person person = (Person) o;
        return id != null && Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
