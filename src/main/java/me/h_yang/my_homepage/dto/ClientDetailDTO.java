package me.h_yang.my_homepage.dto;

import me.h_yang.my_homepage.entity.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;


public class ClientDetailDTO implements UserDetails {

    private final Client client;

    public ClientDetailDTO(Client client) {
        this.client = client;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return client.getAuthorities();
    }

    @Override
    public String getPassword() {
        return client.getPassword();
    }

    @Override
    public String getUsername() {
        return client.getEmail();
    }
}
