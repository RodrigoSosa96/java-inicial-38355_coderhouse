package com.coderhouse.proyectofinal.exception;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -3332292346834265371L;
    private final String username;

    public static UserNotFoundException createWith(String username) {
        return new UserNotFoundException(username);
    }
    public static UserNotFoundException createWith(Long id) {
        return new UserNotFoundException(id.toString());
    }

    private UserNotFoundException(String username) {
        this.username = username;
    }

    @Override
    public String getMessage() {
        return "Cliente '" + username + "' no encontrado";
    }

}
