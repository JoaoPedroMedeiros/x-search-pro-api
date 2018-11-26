package com.jpcami.tads.xsearchpro.api.util;

import java.util.Arrays;
import java.util.List;

public class SimpleResult {

    private String message;

    private List<String> errorMessages;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public static SimpleResult success() {
        SimpleResult result = new SimpleResult();
        result.setMessage("Operação realizada com sucesso");
        return result;
    }

    public static SimpleResult validation(String... messages) {
        SimpleResult result = new SimpleResult();
        result.setMessage("Erro de validação");
        result.setErrorMessages(Arrays.asList(messages));
        return result;
    }

    public static SimpleResult validation(List<String> messages) {
        SimpleResult result = new SimpleResult();
        result.setMessage("Erro de validação");
        result.setErrorMessages(messages);
        return result;
    }
}
