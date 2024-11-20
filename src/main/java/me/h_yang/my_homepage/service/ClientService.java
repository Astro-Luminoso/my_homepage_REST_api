package me.h_yang.my_homepage.service;

import me.h_yang.my_homepage.Exception.ClientNotFoundException;
import me.h_yang.my_homepage.dto.ClientDetailDTO;
import me.h_yang.my_homepage.entity.Client;
import me.h_yang.my_homepage.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for handling client data.
 */
@Service
public class ClientService {

    private final ClientRepository clientRepository;


    /**
     * Constructor for the ClientService class.
     *
     * @param clientRepository The client repository.
     */
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Check if the user exists in the database.
     *
     * @param email The email of the user.
     * @return true if the user exists, false otherwise.
     */
    public boolean checkUser(String email) {

        return clientRepository.findByEmail(email) != null;
    }

    /**
     * Find the client by email.
     *
     * @param email The email of the client.
     * @return The client detail DTO.
     */
    public ClientDetailDTO findClientByEmail(String email) {

        Client clientData = clientRepository.findByEmail(email);

        if (clientData == null) {
            System.out.println("Client no no");
            throw new ClientNotFoundException(email);
        }

        return new ClientDetailDTO(clientData);
    }
}
