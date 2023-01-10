package com.dev.apitestdevjr.address.enums;

public enum TypeAddress {
    SIM(0, "main"),
    NAO(1, "secondary");

    private Integer code;
    private String type;


    private TypeAddress(Integer code, String type) {
        this.code = code;
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}