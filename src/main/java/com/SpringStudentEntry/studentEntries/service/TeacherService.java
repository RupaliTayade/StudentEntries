package com.SpringStudentEntry.studentEntries.service;

import com.SpringStudentEntry.studentEntries.dto.TeacherDto;
import com.SpringStudentEntry.studentEntries.entity.Teacher;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TeacherService {
    List<TeacherDto> findAll();

    Teacher create(TeacherDto teacher);

    TeacherDto findById(String id);

    TeacherDto update(String id, TeacherDto teacher, MultipartFile image) throws IOException;

    void delete(String id);
}
