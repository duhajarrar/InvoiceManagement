package com.example.invoicemanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name="creation_date")
    private Date creationDate;

    @Column
    private Double total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "invoice_id")
    private List<InvoiceItems> items;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    public Invoice() {
        this.items=new ArrayList<InvoiceItems>();
    }

    public Invoice(String title, String description, Date creationDate, Double total, Customer customer, List<InvoiceItems> items) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.total = total;
        this.customer = customer;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @XmlTransient
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public List<InvoiceItems> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItems> items) {
        this.items = items;
    }

    public void addItem(InvoiceItems item) {
        this.items.add(item);
    }

    public void deleteItem(InvoiceItems item) {
        this.items.remove(items.indexOf(item));
    }

    public Double getTotal() {
        this.total = 0.0;
        for(int i = 0; i < items.size(); i++) {
            this.total += items.get(i).calculateTotal();
        }
        return this.total;
    }


    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", total=" + total +
                ", items=" + items +
                '}';
    }
}
