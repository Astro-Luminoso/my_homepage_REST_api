package me.h_yang.my_homepage.service;

import me.h_yang.my_homepage.dto.ClientDetailDTO;
import me.h_yang.my_homepage.entity.Client;
import me.h_yang.my_homepage.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean checkUser(String email) {

        return clientRepository.findByEmail(email) != null;
    }



    public ClientDetailDTO loadUserByUsername(String username) throws UsernameNotFoundException {

        Client clientData = clientRepository.findByEmail(username);

        return clientData != null ? new ClientDetailDTO(clientData) : null;
    }
}
