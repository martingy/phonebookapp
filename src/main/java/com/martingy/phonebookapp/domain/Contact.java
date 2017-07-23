package com.martingy.phonebookapp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Entity containing data of a contact in the phone book.
 */
@Entity
public class Contact implements Serializable {

    private static final long serialVersionUID = 8659074692633594002L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(length = 50, nullable = false)
    private String name;

    @NotNull
    @Column(length = 50, nullable = false)
    private String phoneNumber;

    @Lob
    private byte[] picture;

    public Contact() {
    }

    public Contact(String name, String phoneNumber, byte[] picture) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return id != null ? id.equals(contact.id) : contact.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Contact{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            '}';
    }
}
