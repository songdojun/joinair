package com.example.joinair.dto.api;


public class ResShippingDocFormat extends ResFormat<ResShippingDoc> {

    public ResShippingDocFormat() {
        super();
    }

    public ResShippingDocFormat(boolean result, int errorCode, String errorMessage, ResShippingDoc foramt) {
        super(result, errorCode, errorMessage, foramt);
    }
}
