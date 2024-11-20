package me.h_yang.my_homepage.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Authority> userRoles;


    // JPA empty constructor
    protected Client() {}

    /**
     * Constructor for a user
     *
     * @param email the email of the user
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param password the password of the user
     */
    public Client(String email, String firstName, String lastName, String password) {

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String LastName) {
        this.lastName = LastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Add a role to a user
     *
     * @param authority the user role to be added
     */
    public void grantAuthority(String authority) {
        if (userRoles == null)
            userRoles = new ArrayList<>();

        userRoles.add(new Authority(authority));
    }

    /**
     * Get the authorities of a user
     *
     * @return the authorities of a user
     */
    public List<GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        this.userRoles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole())));

        return authorities;
    }
}
