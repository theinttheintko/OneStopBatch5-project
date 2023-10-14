package com.example.bookshop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "customer" , cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<BookItems> bookItems = new HashSet<>();

    public void addBook(BookItems bookItems) {
        bookItems.setCustomer(this);
        this.bookItems.add(bookItems);
    }
}
