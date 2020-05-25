package com.SpringStudentEntry.studentEntries.mapper;

import com.SpringStudentEntry.studentEntries.dto.StudentDto;
import com.SpringStudentEntry.studentEntries.dto.TeacherDto;
import com.SpringStudentEntry.studentEntries.entity.Student;
import com.SpringStudentEntry.studentEntries.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class StudentMapperTest {
    @InjectMocks
    StudentMapper studentMapper;
    @Mock
    TeacherMapper teacherMapper;

    @Test
    void dtoToStudent() {
        TeacherDto teacher1 = new TeacherDto();
        StudentDto testDto1 = new StudentDto(1L, "John", "john@gmail.com", 2L, teacher1, "byte");
        assertNotNull(studentMapper.dtoToStudent(testDto1));
    }

    @Test
    void studentToDto() {
        Teacher teacher = new Teacher();
        Student studentEntityToReturnFromFindById = new Student(1L, "John", "john@gmail.com", 2L, new byte[1], teacher);
        assertNotNull(studentMapper.studentToDto(studentEntityToReturnFromFindById));
    }

    @Test
    void studentDtoList() {
        Teacher teacher = new Teacher();
        Student testEntity1 = new Student(1L, "John", "john@gmail.com", 2L, new byte[1], teacher);
        Student testEntity2 = new Student(3L, "Alex", "alex@gmail.com", 4L, new byte[1], teacher);

        List<Student> studentDtoListToReturnFromMapper = new ArrayList<>();
        studentDtoListToReturnFromMapper.add(testEntity1);
        studentDtoListToReturnFromMapper.add(testEntity2);
        assertNotNull(studentMapper.studentDtoList(studentDtoListToReturnFromMapper));
    }
}