package com.ernie.mock_instagram_server.exception;

public class UserAlreadyExistsException extends Exception{

    private final String username;

    public UserAlreadyExistsException(String username) {
        this.username = username;
    }

    @Override
    public String getMessage() {
        return String.format("Username already exists: \"%s\"", username);
    }
}
