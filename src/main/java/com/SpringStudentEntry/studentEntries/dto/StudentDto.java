package com.SpringStudentEntry.studentEntries.dto;

public class StudentDto {
    private Long id;
    private String name;
    private String email;
    private long phoneNo;
    private TeacherDto teacherDto;
    private String img;

    public StudentDto() {
    }

    public StudentDto(Long id, String name, String email, long phoneNo, TeacherDto teacherDto, String img) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.teacherDto = teacherDto;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
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

    public TeacherDto getTeacherDto() {
        return teacherDto;
    }

    public void setTeacherDto(TeacherDto teacherDto) {
        this.teacherDto = teacherDto;
    }
}
