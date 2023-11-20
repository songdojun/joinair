package com.example.joinair.dto.api;

public abstract class ResFormat<T> {

    protected boolean result;
    protected int errorCode;
    protected String errorMessage;
    protected T format;

    public ResFormat() {
        super();
    }

    public ResFormat(boolean result, int errorCode, String errorMessage, T format) {
        this.result = result;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.format = format;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public T getFormat() {
        return format;
    }

    public void setFormat(T format) {
        this.format = format;
    }
}
