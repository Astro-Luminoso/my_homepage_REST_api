package me.h_yang.my_homepage.repository;

import me.h_yang.my_homepage.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for the User entity
 */
@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    /**
     * Find all users by ID
     *
     * @param id user Id
     * @return User object
     */
    Client findById(long id);


    /**
     * Find all users by email
     *
     * @param email user email
     * @return User object
     */
    Client findByEmail(String email);


    /**
     * Find all users by user email
     *
     * @param email user email
     * @return true if the email is already exists, false otherwise
     */
    Boolean existsByEmail(String email);
}
