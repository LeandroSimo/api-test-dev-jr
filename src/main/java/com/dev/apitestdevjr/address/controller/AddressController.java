package com.dev.apitestdevjr.address.controller;

import com.dev.apitestdevjr.address.dtos.AddressDto;
import com.dev.apitestdevjr.address.service.AddressService;
import com.dev.apitestdevjr.exception.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/address")
public class AddressController {
    public static final String ID = "/{id}";

    private final AddressService addressService;

    //Lista todos os endereços com paginação
    @GetMapping
    public Response<Page<AddressDto>> findALlPerson(Pageable pageable) {
        Response<Page<AddressDto>> response = new Response<>();
        response.setData(addressService.findALlAddress(pageable));
        response.setStatusCode(HttpStatus.OK.value());
        return response;
    }
    //Salva Endereço
    @PostMapping(value = "/register")
    public ResponseEntity<Response<Boolean>> createUser(@RequestBody AddressDto address) {
        Response<Boolean> response = new Response<>();
        response.setData(this.addressService.saveAddress(address));
        response.setStatusCode(HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    //Busca Endereço por ID
    @GetMapping(value = ID)
    public ResponseEntity<Response<AddressDto>> findAddressById(@PathVariable Long id) {
        Response<AddressDto> response = new Response<>();
        response.setData(this.addressService.findByIdAddress(id));
        response.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
