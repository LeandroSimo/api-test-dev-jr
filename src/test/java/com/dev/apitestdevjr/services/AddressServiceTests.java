package com.dev.apitestdevjr.services;

import com.dev.apitestdevjr.address.dtos.AddressDto;
import com.dev.apitestdevjr.address.model.Address;
import com.dev.apitestdevjr.address.repositories.AddressRepository;
import com.dev.apitestdevjr.address.service.AddressService;
import com.dev.apitestdevjr.person.PersonException.PersonException;
import com.dev.apitestdevjr.person.dtos.PersonDtoFull;
import com.dev.apitestdevjr.person.model.Person;
import com.dev.apitestdevjr.utils.address.AddressCreator;
import com.dev.apitestdevjr.utils.address.AddressPostRequestBodyCreator;
import com.dev.apitestdevjr.utils.person.PersonCreator;
import com.dev.apitestdevjr.utils.person.PersonPostRequestBodyCreator;
import com.dev.apitestdevjr.utils.person.PersonPutRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@ExtendWith(SpringExtension.class)

public class AddressServiceTests {
    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepositoryMock;

    @BeforeEach
    void setUP() {

        PageImpl<Address> addressesPage = new PageImpl<>(List.of(AddressCreator.createValidAddress()));

        BDDMockito.when(addressRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(addressesPage);

        BDDMockito.when(addressRepositoryMock.findAll()).thenReturn(List.of(AddressCreator.createValidAddress()));

        BDDMockito.when(addressRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(AddressCreator.createValidAddress()));

        BDDMockito.when(addressRepositoryMock.save(ArgumentMatchers.any(Address.class))).thenReturn(AddressCreator.createValidAddress());

        BDDMockito.doNothing().when(addressRepositoryMock).delete(ArgumentMatchers.any(Address.class));

    }

    @Test
    @DisplayName(value = "Retorna a lista de endereços dentro do objeto da página quando bem-sucedido")
    public void listAllReturnsListOfPersonsInsidePageObjectWhenSuccessful() {
        String expectedNameStreet = AddressCreator.createAddressToBeSaved().getStreet();
        Page<AddressDto> addressDto = addressService.findALlAddress(PageRequest.of(1, 1));

        assertThat(addressDto).isNotNull();
        assertThat(addressDto.toList())
                .isNotEmpty()
                .hasSize(1);
        assertThat(addressDto.toList().get(0).getStreet()).isEqualTo(expectedNameStreet);

    }

    @Test
    @DisplayName(value = "Lança PersonException quando o endereço não é encontrado")
    public void findByIdOrAddressExceptionWhenPersonIsNotFound() {

        BDDMockito.when(addressRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(PersonException.class).isThrownBy(() -> this.addressService.findByIdAddress(1L));

    }

    @Test
    @DisplayName(value = "Retorna endereço quando bem sucedido")
    public void saveReturnsPersonWhenSuccessful() {
        boolean address = addressService.saveAddress(AddressPostRequestBodyCreator.createAddressPostRequestBody());
        assertThat(address)
                .isEqualTo(true)
                .isNotNull();

    }


}
