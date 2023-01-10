package com.dev.apitestdevjr.utils.address;

import com.dev.apitestdevjr.address.model.Address;

public class AddressCreator {
    public static Address createAddressToBeSaved() {
        return Address.builder()
                .street("rua")
                .build();
    }

    public static Address createValidAddress() {
        return Address.builder()
                .id(1L)
                .street("rua")
                .build();
    }

    public static Address createValidUpdateAddress() {
        return Address.builder()
                .id(1L)
                .street("rua nova")
                .build();
    }
}
