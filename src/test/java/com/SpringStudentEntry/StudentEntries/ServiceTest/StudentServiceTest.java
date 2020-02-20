package com.SpringStudentEntry.StudentEntries.ServiceTest;

import com.SpringStudentEntry.StudentEntries.dto.StudentDto;
import com.SpringStudentEntry.StudentEntries.dto.TeacherDto;
import com.SpringStudentEntry.StudentEntries.entity.Student;
import com.SpringStudentEntry.StudentEntries.entity.Teacher;
import com.SpringStudentEntry.StudentEntries.mapper.StudentMapper;
import com.SpringStudentEntry.StudentEntries.mapper.TeacherMapper;
import com.SpringStudentEntry.StudentEntries.repository.StudentRepository;
import com.SpringStudentEntry.StudentEntries.repository.TeacherRepository;
import com.SpringStudentEntry.StudentEntries.service.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
//        StudentDto studentDtoToReturnFromMapper = new StudentDto(1L, "John", "john@gmail.com", 2L, teacher1, "byte");
//        when(studentRepository.findById(1L)).thenReturn(Optional.of(studentEntityToReturnFromFindById));
//        when(studentMapper.studentToDto(Optional.of(studentEntityToReturnFromFindById).get())).thenReturn(studentDtoToReturnFromMapper);
//
//        StudentDto returnedStudentDtoFromService = studentService.findById("1");
//
//        assertNotNull(returnedStudentDtoFromService);
//        verify(studentRepository, times(1)).findById(1L);
//        assertEquals(studentDtoToReturnFromMapper, returnedStudentDtoFromService);


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
        Student fromRepository = new Student(1L, "John", "john@gmail.com", 2L, null, teacher);
//        Student studentToReturnFromMapper = new Student(1L, "John", "john@gmail.com", 2L, new byte[1], teacher);
//        Student studentToReturnFromSave = new Student(1L, "John", "john@gmail.com", 2L, new byte[1], teacher);
//
//        when(studentRepository.findById(any())).thenReturn(Optional.of(FromRepository));
//        when(studentMapper.dtoToStudent(any())).thenReturn(studentToReturnFromMapper);
//        when(studentRepository.save(any())).thenReturn(studentToReturnFromSave);
     MultipartFile image = new MockMultipartFile("name", (byte[]) any());
//
//    StudentDto resultDtoFromUpdate = studentService.update("1", null, studentDtoToUpdate, image);
//
//        verify(studentRepository, times(1)).save(studentToReturnFromMapper);
//        assertNotNull(resultDtoFromUpdate);
//        assertEquals(studentDtoToUpdate, resultDtoFromUpdate);

        when(studentRepository.findById(any())).thenReturn(Optional.of(fromRepository));
        when(teacherRepository.findById(any())).thenReturn(Optional.of(teacher));
        studentService.update("1", "1", studentDtoToUpdate, eq(image));
        verify(studentRepository, atLeastOnce()).save(fromRepository);
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
