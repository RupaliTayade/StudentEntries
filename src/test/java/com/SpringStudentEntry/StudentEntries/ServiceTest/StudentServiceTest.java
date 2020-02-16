package com.SpringStudentEntry.StudentEntries.ServiceTest;

import com.SpringStudentEntry.StudentEntries.dto.StudentDto;
import com.SpringStudentEntry.StudentEntries.dto.TeacherDto;
import com.SpringStudentEntry.StudentEntries.entity.Student;
import com.SpringStudentEntry.StudentEntries.entity.Teacher;
import com.SpringStudentEntry.StudentEntries.mapper.StudentMapper;
import com.SpringStudentEntry.StudentEntries.repository.StudentRepository;
import com.SpringStudentEntry.StudentEntries.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private StudentMapper studentMapper;

    @Test
    public void testGetAllStudents() {
        Teacher teacher = new Teacher();
        Student testEntity1 = new Student(1L, "John", "john@gmail.com", 2L, new byte[1], teacher);
        Student testEntity2 = new Student(3L, "Alex", "alex@gmail.com", 4L, new byte[1], teacher);

        List<Student> studentListToReturnFromRepository = new ArrayList<>();
        studentListToReturnFromRepository.add(testEntity1);
        studentListToReturnFromRepository.add(testEntity2);
        TeacherDto teacher1 = new TeacherDto();
        // Same values as testEntity1 and testEntity2
        StudentDto testDto1 = new StudentDto(1L, "John", "john@gmail.com", 2L, teacher1, "byte");
        StudentDto testDto2 = new StudentDto(3L, "Alex", "alex@gmail.com", 4L, teacher1, "byte");

        List<StudentDto> studentDtoListToReturnFromMapper = new ArrayList<>();
        studentDtoListToReturnFromMapper.add(testDto1);
        studentDtoListToReturnFromMapper.add(testDto2);

        when(studentRepository.findAll()).thenReturn(studentListToReturnFromRepository);
        when(studentMapper.studentDtoList(studentListToReturnFromRepository)).thenReturn(studentDtoListToReturnFromMapper);

        List<StudentDto> resultProjectDtoListFromGetAll = studentService.findAll();

        verify(studentRepository, times(1)).findAll();
        assertNotNull(resultProjectDtoListFromGetAll);
        assertEquals(studentDtoListToReturnFromMapper, resultProjectDtoListFromGetAll);
    }

    @Test
    public void testStudentById() {
        Teacher teacher = new Teacher();
        TeacherDto teacher1 = new TeacherDto();
        Student studentEntityToReturnFromFindById = new Student(1L, "John", "john@gmail.com", 2L, new byte[1], teacher);
        StudentDto studentDtoToReturnFromMapper = new StudentDto(1L, "John", "john@gmail.com", 2L, teacher1, "byte");
        when(studentRepository.findById(1L)).thenReturn(Optional.of(studentEntityToReturnFromFindById));
        when(studentMapper.studentToDto(Optional.of(studentEntityToReturnFromFindById).get())).thenReturn(studentDtoToReturnFromMapper);

        StudentDto returnedStudentDtoFromService = studentService.findById("1");

        assertNotNull(returnedStudentDtoFromService);
        verify(studentRepository, times(1)).findById(1L);
        assertEquals(studentDtoToReturnFromMapper, returnedStudentDtoFromService);
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
        Teacher teacher = new Teacher();
        TeacherDto teacher1 = new TeacherDto();
        StudentDto studentDtoToUpdate = new StudentDto(1L, "John", "john@gmail.com", 2L, teacher1, "byte");
        Student FromRepository = new Student(1L, "John", "john@gmail.com", 2L, new byte[1], teacher);
        Student studentToReturnFromMapper = new Student(1L, "John", "john@gmail.com", 2L, new byte[1], teacher);
        Student studentToReturnFromSave = new Student(1L, "John", "john@gmail.com", 2L, new byte[1], teacher);

        when(studentRepository.findById(any())).thenReturn(Optional.of(FromRepository));
        when(studentMapper.dtoToStudent(any())).thenReturn(studentToReturnFromMapper);
        when(studentRepository.save(any())).thenReturn(studentToReturnFromSave);
        MultipartFile image = new MockMultipartFile("name", (byte[]) any());

        StudentDto resultDtoFromUpdate = studentService.update("1", "1", studentDtoToUpdate, image);

        verify(studentRepository, times(1)).save(studentToReturnFromMapper);
        assertNotNull(resultDtoFromUpdate);
        assertEquals(studentDtoToUpdate, resultDtoFromUpdate);
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
