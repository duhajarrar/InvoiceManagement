package com.example.invoicemanagement.repository;

import com.example.invoicemanagement.model.Invoice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
    //    Invoice findByDate(Date date);

    @Query("select i from Invoice i join fetch i.customer c join fetch i.items l join fetch l.item where i.id = ?1")
    public Invoice fetchByIdWithCustomerWithInvoiceItemsWithItem(Long id);
}