package com.SpringStudentEntry.studentEntries.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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



    public Teacher() {
    }

    public Teacher(long id, String name, String surname, byte[] image) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.image = image;
    }

    public static TeacherBuilder builder() {
        return new TeacherBuilder();
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

    public static class TeacherBuilder {
        private long id;
        private String name;
        private String surname;
        private byte[] image;

        TeacherBuilder() {
        }

        public TeacherBuilder id(long id) {
            this.id = id;
            return this;
        }

        public TeacherBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TeacherBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public TeacherBuilder image(byte[] image) {
            this.image = image;
            return this;
        }

        public Teacher build() {
            return new Teacher(id, name, surname, image);
        }

        public String toString() {
            return "Teacher.TeacherBuilder(id=" + this.id + ", name=" + this.name + ", surname=" + this.surname + ", image=" + java.util.Arrays.toString(this.image) + ")";
        }
    }
}
