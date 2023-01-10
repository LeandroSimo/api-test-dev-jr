package com.dev.apitestdevjr.address.requests;

import com.dev.apitestdevjr.address.dtos.AddressDto;
import com.dev.apitestdevjr.address.enums.TypeAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressPostRequestBody extends AddressDto {

    private String street;

}
