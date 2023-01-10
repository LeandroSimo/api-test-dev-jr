package com.dev.apitestdevjr.services;

import com.dev.apitestdevjr.person.PersonException.PersonException;
import com.dev.apitestdevjr.person.dtos.PersonDtoFull;
import com.dev.apitestdevjr.person.model.Person;
import com.dev.apitestdevjr.person.repositories.PersonRepository;
import com.dev.apitestdevjr.person.services.PersonService;
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
public class PersonServiceTests {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepositoryMock;

    @BeforeEach
    void setUP() {

        PageImpl<Person> personPage = new PageImpl<>(List.of(PersonCreator.createValidPerson()));

        BDDMockito.when(personRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(personPage);

        BDDMockito.when(personRepositoryMock.findAll()).thenReturn(List.of(PersonCreator.createValidPerson()));

        BDDMockito.when(personRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(PersonCreator.createValidPerson()));

        BDDMockito.when(personRepositoryMock.save(ArgumentMatchers.any(Person.class))).thenReturn(PersonCreator.createValidPerson());

        BDDMockito.doNothing().when(personRepositoryMock).delete(ArgumentMatchers.any(Person.class));

    }

    @Test
    @DisplayName(value = "Retorna a lista de pessoas dentro do objeto da página quando bem-sucedido")
    public void listAllReturnsListOfPersonsInsidePageObjectWhenSuccessful() {
        String expectedName = PersonCreator.createPersonToBeSaved().getName();

        Page<PersonDtoFull> personPage = personService.findALlPerson(PageRequest.of(1, 1));

        assertThat(personPage).isNotNull();
        assertThat(personPage.toList())
                .isNotEmpty()
                .hasSize(1);
        assertThat(personPage.toList().get(0).getName()).isEqualTo(expectedName);

    }

    @Test
    @DisplayName(value = "Lança PersonException quando a pessoa não é encontrada")
    public void findByIdOrPersonExceptionWhenPersonIsNotFound() {

        BDDMockito.when(personRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(PersonException.class).isThrownBy(() -> this.personService.findByIdPerson(1L));

    }

    @Test
    @DisplayName(value = "Retorna pessoa quando bem sucedido")
    public void saveReturnsPersonWhenSuccessful() {
        boolean person = personService.savePerson(PersonPostRequestBodyCreator.createPersonPostRequestBody());
        assertThat(person)
                .isEqualTo(true)
                .isNotNull();

    }

    @Test
    @DisplayName(value = "Substitui as atualizações da pessoa quando for bem-sucedido")
    public void replaceUpdatesPersonWhenSuccessful() {

        assertThatCode(() -> personService.updatePerson(PersonPutRequestBodyCreator.createPersonPutRequestBody()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName(value = "Remove o anime quando bem-sucedido")
    public void deleteRemovesPersonWhenSuccessful(){
        assertThatCode(() -> personService.deletePerson(1L))
                .doesNotThrowAnyException();
    }
}
