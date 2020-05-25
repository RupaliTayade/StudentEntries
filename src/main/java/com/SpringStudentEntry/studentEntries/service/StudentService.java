package com.SpringStudentEntry.studentEntries.service;

import com.SpringStudentEntry.studentEntries.dto.StudentDto;
import com.SpringStudentEntry.studentEntries.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentService {
    List<StudentDto> findAll();

    Student create(StudentDto student);

    StudentDto findById(String id);

    StudentDto update(String teacherId, String id, StudentDto student, MultipartFile image) throws IOException;

    void delete(String id);
}
