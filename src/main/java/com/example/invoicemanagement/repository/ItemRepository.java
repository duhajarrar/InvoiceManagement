package com.example.invoicemanagement.repository;

import com.example.invoicemanagement.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("select i from Item i where i.title like %?1%")
    public List<Item> findByTitle(String title);
//    public Item findById(Long id);

}
