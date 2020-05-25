package com.SpringStudentEntry.studentEntries.service;

import com.SpringStudentEntry.studentEntries.dto.StudentDto;
import com.SpringStudentEntry.studentEntries.dto.TeacherDto;
import com.SpringStudentEntry.studentEntries.entity.Student;
import com.SpringStudentEntry.studentEntries.entity.Teacher;
import com.SpringStudentEntry.studentEntries.mapper.StudentMapper;
import com.SpringStudentEntry.studentEntries.mapper.TeacherMapper;
import com.SpringStudentEntry.studentEntries.repository.StudentRepository;
import com.SpringStudentEntry.studentEntries.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class StudentServiceTest {
    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;
    @Mock
    TeacherRepository teacherRepository;

    @Mock
    TeacherMapper teacherMapper;

    @Test
    public void testGetAllStudents() {
        Teacher teacher = new Teacher();
        Student testEntity1 = new Student(1L, "John", "john@gmail.com", 2L, new byte[1], teacher);

        studentRepository.save(testEntity1);

       studentService.findAll();

        verify(studentRepository, atLeastOnce()).findAll();

    }

    @Test
    public void testStudentById() {
        Teacher teacher = new Teacher();
        TeacherDto teacher1 = new TeacherDto();
        Student studentEntityToReturnFromFindById = new Student(1L, "John", "john@gmail.com", 2L, new byte[1], teacher);

        when(studentRepository.findById(any())).thenReturn(Optional.of(studentEntityToReturnFromFindById));
        studentService.findById("1");
        verify(studentRepository, atLeastOnce()).findById(any());
    }

    @Test
    public void testSaveStudent() {
        Teacher teacher = new Teacher();
        TeacherDto teacher1 = new TeacherDto();
        StudentDto studentDtoToSave = new StudentDto(1L, "John", "john@gmail.com", 2L, teacher1, "byte");
        Student studentEntityToReturnFromMapper = new Student(1L, "John", "john@gmail.com", 2L, new byte[1], teacher);
        Student studentEntityToReturnFromSave = new Student(1L, "John", "john@gmail.com", 2L, new byte[1], teacher);

        when(studentMapper.dtoToStudent(studentDtoToSave)).thenReturn(studentEntityToReturnFromMapper);
        when(studentRepository.save(studentEntityToReturnFromMapper)).thenReturn(studentEntityToReturnFromSave);

        Student returnedDtoFromService = studentService.create(studentDtoToSave);

        verify(studentRepository, times(1)).save(studentEntityToReturnFromMapper);
        assertNotNull(returnedDtoFromService);
    }

    @Test
    public void testUpdateStudentById() throws IOException {
        Teacher teacher = new Teacher( 1L,"johi", "smith", null );
      TeacherDto teacher1 = new TeacherDto();
      StudentDto studentDtoToUpdate = new StudentDto(1L, "John", "john@gmail.com", 2L, teacher1, null);
        when(teacherRepository.findById(any())).thenReturn(Optional.of(teacher));
          MultipartFile image = new MockMultipartFile("name", (byte[]) any());

        assertNotNull(studentService.update("1", "1", studentDtoToUpdate, image));

    }

    @Test
    public void testDeleteStudent() throws IOException {
        Teacher teacher = new Teacher();
        Student studentEntityToReturnFromMapper = new Student(1L, "John", "john@gmail.com", 2L, new byte[1], teacher);

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(studentEntityToReturnFromMapper));
        studentService.delete("1");
        verify(studentRepository, times(1)).delete(studentEntityToReturnFromMapper);
    }
}
