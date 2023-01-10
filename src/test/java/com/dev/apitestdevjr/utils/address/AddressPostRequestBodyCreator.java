package com.dev.apitestdevjr.utils.address;

import com.dev.apitestdevjr.address.requests.AddressPostRequestBody;

public class AddressPostRequestBodyCreator {

    public static AddressPostRequestBody createAddressPostRequestBody(){
        return AddressPostRequestBody.builder()
                .street(AddressCreator.createAddressToBeSaved().getStreet())
                .build();
    }
}
