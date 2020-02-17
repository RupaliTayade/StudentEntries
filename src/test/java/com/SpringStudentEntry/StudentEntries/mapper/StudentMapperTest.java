package com.SpringStudentEntry.StudentEntries.mapper;

import com.SpringStudentEntry.StudentEntries.dto.StudentDto;
import com.SpringStudentEntry.StudentEntries.dto.TeacherDto;
import com.SpringStudentEntry.StudentEntries.entity.Student;
import com.SpringStudentEntry.StudentEntries.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudentMapperTest {
    @Autowired
    StudentMapper studentMapper;
    @MockBean
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