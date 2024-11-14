package me.h_yang.my_homepage.repository;

import me.h_yang.my_homepage.entity.Authority;
import me.h_yang.my_homepage.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for the User entity
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Find all users by ID
     *
     * @param id user Id
     * @return User object
     */
    User findById(long id);


    /**
     * Find all users by email
     *
     * @param email user email
     * @return User object
     */
    User findByEmail(String email);


}
