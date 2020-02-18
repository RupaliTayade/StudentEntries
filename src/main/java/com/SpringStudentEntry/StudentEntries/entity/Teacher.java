package com.SpringStudentEntry.StudentEntries.entity;

import javax.persistence.*;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

    public Teacher(Builder builder) {
           this.id = id;
        this.name = name;
        this.surname = surname;
        this.image = image;
    }

    public Teacher() {
    }

    public Teacher(long id, String name, String surname, byte[] image) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public static class Builder {
        private long id;
        private String name;
        private String surname;
        private byte[] image;

        public Builder  id(long id) {
            this.id = id;
            return this;
        }

        public Builder  name(String name) {
            this.name = name;
            return this;
        }

        public Builder  image(byte[] image) {
            this.image = image;
            return this;
        }

        public Builder surname(String surname) {
            this.surname = surname;
            return this;
        }
        public Teacher build() {
            return new Teacher(this);
        }
    }
}
