package com.ernie.mock_instagram_server.controller.api.response;

import java.util.ArrayList;
import java.util.List;

public class ExceptionResponseBody {

    List<String> errors = new ArrayList<>();

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
