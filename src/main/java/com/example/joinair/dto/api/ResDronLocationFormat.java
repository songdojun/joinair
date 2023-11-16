package com.example.joinair.dto.api;


public class ResDronLocationFormat extends ResFormat<ResDronLocation> {

    public ResDronLocationFormat() {
        super();
    }

    public ResDronLocationFormat(boolean result, int errorCode, String errorMessage, ResDronLocation foramt) {
        super(result, errorCode, errorMessage, foramt);
    }
}
