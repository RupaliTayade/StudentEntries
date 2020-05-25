package com.SpringStudentEntry.studentEntries.dto;

public class TeacherDto {
    private Long id;
    private String name;
    private String surname;
    private String img;

    public TeacherDto() {
    }

    public TeacherDto(Long id, String name, String surname, String img) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
