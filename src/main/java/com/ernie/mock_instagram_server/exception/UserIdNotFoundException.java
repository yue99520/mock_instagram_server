package com.ernie.mock_instagram_server.exception;

public class UserIdNotFoundException extends Exception {

    private final int userId;

    public UserIdNotFoundException(int userId) {
        this.userId = userId;
    }

    @Override
    public String getMessage() {
        return String.format("User id is not exist: \"%d\"", userId);
    }
}
