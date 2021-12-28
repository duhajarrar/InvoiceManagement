package com.example.invoicesmanager.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attachment_id", nullable = false)
    private Long attachment_id;

    @Column(name = "invoice_id", nullable = false)
    private Long invoice_id;

    @Column
    private String attachmentType;

    @Column
    private Date creationDate;

    @Column
    private String attachmentSize;

    public Attachment() {

    }

    public Attachment(Long attachment_id, Long invoice_id, String attachmentType, Date creationDate, String attachmentSize) {
        this.attachment_id = attachment_id;
        this.invoice_id = invoice_id;
        this.attachmentType = attachmentType;
        this.creationDate = creationDate;
        this.attachmentSize = attachmentSize;
    }


    public Long getAttachment_id() {
        return attachment_id;
    }

    public void setAttachment_id(Long attachment_id) {
        this.attachment_id = attachment_id;
    }

    public Long getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(Long invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getAttachmentSize() {
        return attachmentSize;
    }

    public void setAttachmentSize(String attachmentSize) {
        this.attachmentSize = attachmentSize;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "attachment_id=" + attachment_id +
                ", invoice_id=" + invoice_id +
                ", attachmentType='" + attachmentType + '\'' +
                ", creationDate=" + creationDate +
                ", attachmentSize='" + attachmentSize + '\'' +
                '}';
    }
}
