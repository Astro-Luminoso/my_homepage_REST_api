package me.h_yang.my_homepage.dto;

import me.h_yang.my_homepage.entity.Client;
import org.springframework.security.core.GrantedAuthority;
import java.util.List;


public class ClientDetailDTO {

    private final Client client;

    public ClientDetailDTO(Client client) {
        this.client = client;
    }

    public List<GrantedAuthority> getAuthorities() {

        return client.getAuthorities();
    }

    public String getPassword() {
        return client.getPassword();
    }

    public String getEmail() {
        return client.getEmail();
    }
}
