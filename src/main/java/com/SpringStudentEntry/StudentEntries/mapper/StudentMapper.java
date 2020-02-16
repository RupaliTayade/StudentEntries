package com.SpringStudentEntry.StudentEntries.mapper;

import com.SpringStudentEntry.StudentEntries.dto.StudentDto;
import com.SpringStudentEntry.StudentEntries.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class StudentMapper {

    @Autowired
    TeacherMapper teacherMapper;

    public Student dtoToStudent(StudentDto studentDto) {
        Student student = new Student();
        if (studentDto.getId() != null) student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setPhoneNo(studentDto.getPhoneNo());
        if (studentDto.getImg() != null) student.setImage(Base64.getDecoder().decode(studentDto.getImg()));
        if (studentDto.getTeacherDto() != null) student.setTeacher(teacherMapper.dtoToTeacher(studentDto.getTeacherDto()));
        return student;
    }

    public StudentDto studentToDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        studentDto.setPhoneNo(student.getPhoneNo());
        if (student.getImage() != null) studentDto.setImg(Base64.getEncoder().encodeToString(student.getImage()));
        if (student.getTeacher() != null) studentDto.setTeacherDto(teacherMapper.teacherToDto(student.getTeacher()));
        return studentDto;
    }

    public List<StudentDto> studentDtoList(List<Student> students) {
        List<StudentDto> list = new ArrayList<>();
        for (Student student : students) {
            list.add(studentToDto(student)); }
        return list;
    }
}
