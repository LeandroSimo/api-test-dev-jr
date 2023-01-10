package com.dev.apitestdevjr.address.service;

import com.dev.apitestdevjr.address.dtos.AddressDto;
import com.dev.apitestdevjr.address.model.Address;
import com.dev.apitestdevjr.address.repositories.AddressRepository;
import com.dev.apitestdevjr.person.PersonException.PersonException;
import com.dev.apitestdevjr.person.model.Person;
import com.dev.apitestdevjr.person.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {
    private static final  String MENSAGEM_ERROR= "Error interno identificado";
    private static final String USER_NAO_ENCONTRADO ="Usuário não encontrado";

   private final PersonService personService;
    private final AddressRepository addressRepository;
    public Page<AddressDto> findALlAddress(Pageable pageable) {
        try {
            Page<Address> address = addressRepository.findAll(pageable);
            return address.map(AddressDto::new);
        } catch (Exception e) {
            throw new PersonException(MENSAGEM_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Boolean saveAddress(AddressDto addressDto) {
        try {
            this.fromAddress(addressDto);
            return true;
        }catch (DataIntegrityViolationException m) {
            throw new PersonException(USER_NAO_ENCONTRADO, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            throw new PersonException(MENSAGEM_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public AddressDto findByIdAddress(Long id) {
        try {
            Optional<Address> userOptional = this.addressRepository.findById(id);
            if (userOptional.isPresent()) {
                Address address = this.addressRepository.findById(id).get();
                return new AddressDto(address);
            }
            throw new PersonException(USER_NAO_ENCONTRADO, HttpStatus.NOT_FOUND);
        } catch (PersonException m) {
            throw m;
        } catch (Exception e) {
            throw new PersonException(MENSAGEM_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Address fromAddress(AddressDto obj) {
        Person person = personService.findByIdPerson(obj.getPerson());
        Address newObj = new Address();
        newObj.setId(obj.getId());
        newObj.setStreet(obj.getStreet());
        newObj.setZipCode(obj.getZipCode());
        newObj.setAddressNumber(obj.getAddressNumber());
        newObj.setCity(obj.getCity());
        newObj.setTypeAddress(obj.getTypeAddress());
        newObj.setPerson(person);
        return addressRepository.save(newObj);
    }

}
