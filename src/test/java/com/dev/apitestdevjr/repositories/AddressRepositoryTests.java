package com.dev.apitestdevjr.repositories;

import com.dev.apitestdevjr.address.model.Address;
import com.dev.apitestdevjr.address.repositories.AddressRepository;
import com.dev.apitestdevjr.person.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AddressRepositoryTests {
    @Autowired
    private AddressRepository addressRepository;

    @Test
    @DisplayName(value = "Salvando endereço no Banco de Dados")
    public void createShouldPersistData() {
        LocalDate localDate = LocalDate.now();
        Person person = new Person("Leandro", localDate);
        Address address = new Address("rua", 123, 123, "cidade", person);
        this.addressRepository.save(address);
        assertThat(address.getId()).isNotNull();
        assertThat(address.getStreet())
                .isEqualTo("rua")
                .isNotEmpty()
                .isNotNull();
        assertThat(address.getZipCode())
                .isEqualTo(123)
                .isNotNull();
        assertThat(address.getAddressNumber())
                .isEqualTo(123)
                .isNotNull();
        assertThat(address.getCity()).isEqualTo("cidade")
                .isNotNull()
                .isNotEmpty();
        assertThat(address.getPerson()).isNotNull();
    }

    @Test
    @DisplayName(value = "Deletando endereço do Banco de Dados")
    public void deleteShouldRemoveData() {
        LocalDate localDate = LocalDate.now();
        Person person = new Person("Leandro", localDate);
        Address address = new Address("rua", 123, 123, "cidade", person);
        this.addressRepository.save(address);
        this.addressRepository.delete(address);
        assertThat(addressRepository.findById(address.getId())).isNull();
    }

    @Test
    @DisplayName(value = "Atualizando endereço no Banco de Dados")
    public void updateShouldChangeAndPersistData() {
        LocalDate localDate = LocalDate.now();
        Person person = new Person("Leandro", localDate);
        Address address = new Address("rua", 123, 123, "cidade", person);
        this.addressRepository.save(address);
        address.setStreet("nova rua");
        address.setZipCode(321);
        address.setAddressNumber(321);
        address.setCity("nova cidade");
        this.addressRepository.save(address);
        assertThat(address.getId()).isNotNull();
        assertThat(address.getStreet())
                .isEqualTo("nova rua")
                .isNotEmpty()
                .isNotNull();
        assertThat(address.getZipCode())
                .isEqualTo(321)
                .isNotNull();
        assertThat(address.getAddressNumber())
                .isEqualTo(321)
                .isNotNull();
        assertThat(address.getCity()).isEqualTo("nova cidade")
                .isNotNull()
                .isNotEmpty();
        assertThat(address.getPerson()).isNotNull();
    }

}
