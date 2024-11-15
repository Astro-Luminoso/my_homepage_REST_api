package me.h_yang.my_homepage.entity;

import jakarta.persistence.*;

@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id")
    private Client client;


    private String role;

    // JPA empty constructor
    protected Authority() {}

    public Authority (String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
