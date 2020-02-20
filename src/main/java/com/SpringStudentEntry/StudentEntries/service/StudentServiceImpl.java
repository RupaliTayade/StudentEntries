package com.SpringStudentEntry.StudentEntries.service;

import com.SpringStudentEntry.StudentEntries.dto.StudentDto;
import com.SpringStudentEntry.StudentEntries.entity.Student;
import com.SpringStudentEntry.StudentEntries.entity.Teacher;
import com.SpringStudentEntry.StudentEntries.mapper.StudentMapper;
import com.SpringStudentEntry.StudentEntries.mapper.TeacherMapper;
import com.SpringStudentEntry.StudentEntries.repository.StudentRepository;
import com.SpringStudentEntry.StudentEntries.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public List<StudentDto> findAll() {
        return studentMapper.studentDtoList(studentRepository.findAll());
    }

    @Override
    public Student create(StudentDto student) {

        return studentRepository.save(studentMapper.dtoToStudent(student));
    }

    @Override
    public StudentDto findById(String id) {
        Student student = studentRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        return studentMapper.studentToDto(student);
    }

    @Override
    public StudentDto update(String teacherId, String id, StudentDto studentDto, MultipartFile image) throws IOException {

        studentDto.setId(Long.valueOf(id));
        if (!teacherId.isEmpty()) {

            Teacher teacher = teacherRepository.findById(Long.valueOf(teacherId)).orElseThrow(() -> new IllegalArgumentException("Invalid teacher Id:" + id));
            studentDto.setTeacherDto(teacherMapper.teacherToDto(teacher));
        }
        if (image.getOriginalFilename() != null)
            studentDto.setImg(java.util.Base64.getEncoder().encodeToString(image.getBytes()));
        studentRepository.save(studentMapper.dtoToStudent(studentDto));

        return studentDto;
    }

    @Override
    public void delete(String id) {
        Student student = studentRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        studentRepository.delete(student);
    }
}
