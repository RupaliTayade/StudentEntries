package com.SpringStudentEntry.StudentEntries.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Arrays;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_no")
    private long phoneNo;

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public Student() {
    }

    public Student(long id, String name, String email, long phoneNo, byte[] image, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.image = image;
        this.teacher = teacher;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public static class StudentBuilder {
        private long id;
        private String name;
        private String email;
        private long phoneNo;
        private byte[] image;
        private Teacher teacher;

        StudentBuilder() {
        }

        public StudentBuilder id(long id) {
            this.id = id;
            return this;
        }

        public StudentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StudentBuilder email(String email) {
            this.email = email;
            return this;
        }

        public StudentBuilder phoneNo(long phoneNo) {
            this.phoneNo = phoneNo;
            return this;
        }

        public StudentBuilder image(byte[] image) {
            this.image = image;
            return this;
        }

        public StudentBuilder teacher(Teacher teacher) {
            this.teacher = teacher;
            return this;
        }

        public Student build() {
            return new Student(id, name, email, phoneNo, image, teacher);
        }

        public String toString() {
            return "Student.StudentBuilder(id=" + this.id + ", name=" + this.name + ", email=" + this.email + ", phoneNo=" + this.phoneNo + ", image=" + Arrays.toString(this.image) + ", teacher=" + this.teacher + ")";
        }
    }
}
