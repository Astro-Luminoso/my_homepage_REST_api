package me.h_yang.my_homepage.Exception;


import org.springframework.security.core.AuthenticationException;

public class ClientNotFoundException extends AuthenticationException {

    public ClientNotFoundException(String email) {
        super("Client not found with email: " + email);
    }

    public ClientNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
