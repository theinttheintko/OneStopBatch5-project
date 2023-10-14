package com.example.bookshop.dao;


import com.example.bookshop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomerDao extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByName(String name);

    /*@Query("select b from Book b inner join b.customer c where c.name = ?1")
    List<Book> findBooksByCustomerName(String name);*/
}
