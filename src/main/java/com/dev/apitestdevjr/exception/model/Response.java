package com.dev.apitestdevjr.exception.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Response<T>{
    private int statusCode;
    private T data;
    private long timeStamp;

    public Response(){
        this.timeStamp = System.currentTimeMillis();
    }

}
