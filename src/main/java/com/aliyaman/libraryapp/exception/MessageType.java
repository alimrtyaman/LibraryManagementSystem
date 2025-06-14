package com.aliyaman.libraryapp.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXIST("1001" , "RECORD NOT FOUND"),
    GENERAL_EXCEPTION("9999" , "GENEL BIR HATA OLUST");

    private String code;
    private String message;


    MessageType(String code, String message) {
        this.code = code;
        this.message=message;
    }
}
