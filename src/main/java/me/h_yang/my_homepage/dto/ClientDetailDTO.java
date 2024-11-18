package me.h_yang.my_homepage.dto;

import me.h_yang.my_homepage.entity.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;


public class ClientDetailDTO {

    private final Client client;

    public ClientDetailDTO(Client client) {
        this.client = client;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {

        return client.getAuthorities();
    }

    public String getPassword() {
        return client.getPassword();
    }

    public String getUsername() {
        return client.getEmail();
    }
}
