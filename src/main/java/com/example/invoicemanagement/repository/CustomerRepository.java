package com.example.invoicemanagement.repository;

import com.example.invoicemanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("select c from Customer c left join fetch c.invoices i where c.id = ?1")
    Customer fetchByIdWithInvoices(Long id);
}
