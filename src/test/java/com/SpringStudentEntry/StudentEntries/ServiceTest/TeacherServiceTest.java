package com.SpringStudentEntry.StudentEntries.ServiceTest;

import com.SpringStudentEntry.StudentEntries.dto.TeacherDto;
import com.SpringStudentEntry.StudentEntries.entity.Teacher;
import com.SpringStudentEntry.StudentEntries.mapper.TeacherMapper;
import com.SpringStudentEntry.StudentEntries.repository.TeacherRepository;
import com.SpringStudentEntry.StudentEntries.service.TeacherService;
import com.SpringStudentEntry.StudentEntries.service.TeacherServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {
    @InjectMocks
    private TeacherServiceImpl teacherService;
    @Mock
    private TeacherRepository teacherRepository;
    @Mock
    private TeacherMapper teacherMapper;

    @Test
    public void testGetAllTeacher() {
        Teacher testEntity1 = new Teacher(1L, "John", "Smith", new byte[1]);
        Teacher testEntity2 = new Teacher(3L, "Alex", "Noha", new byte[1]);

        List<Teacher> teacherListToReturnFromRepository = new ArrayList<>();
        teacherListToReturnFromRepository.add(testEntity1);
        teacherListToReturnFromRepository.add(testEntity2);
        // Same values as testEntity1 and testEntity2
        TeacherDto testDto1 = new TeacherDto(1L, "John", "Smith", "byte");
        TeacherDto testDto2 = new TeacherDto(3L, "Alex", "Noha", "byte");

        List<TeacherDto> teacherDtoListToReturnFromMapper = new ArrayList<>();
        teacherDtoListToReturnFromMapper.add(testDto1);
        teacherDtoListToReturnFromMapper.add(testDto2);

        when(teacherRepository.findAll()).thenReturn(teacherListToReturnFromRepository);

        List<TeacherDto> resultProjectDtoListFromGetAll = teacherService.findAll();

        verify(teacherRepository, times(1)).findAll();
        assertNotNull(resultProjectDtoListFromGetAll);
    }

    @Test
    public void testTeacherById() {


        Teacher teacherEntityToReturnFromFindById = new Teacher(1L, "John", "Smith", new byte[1]);
        TeacherDto teacherDtoToReturnFromMapper = new TeacherDto(1L, "John", "Smith", "byte");
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacherEntityToReturnFromFindById));
        when(teacherMapper.teacherToDto(Optional.of(teacherEntityToReturnFromFindById).get())).thenReturn(teacherDtoToReturnFromMapper);

        TeacherDto returnedTeacherDtoFromService = teacherService.findById("1");

        assertNotNull(returnedTeacherDtoFromService);
        verify(teacherRepository, times(1)).findById(1L);
        assertEquals(teacherDtoToReturnFromMapper, returnedTeacherDtoFromService);
    }

    @Test
    public void testSaveTeacher() {

        TeacherDto teacherDtoToSave = new TeacherDto(1L, "John", "Smith", "byte");
        Teacher teacherEntityToReturnFromMapper = new Teacher(1L, "John", "Smith", new byte[1]);
        Teacher teacherEntityToReturnFromSave = new Teacher(1L, "John", "Smith", new byte[1]);
        when(teacherRepository.save(teacherEntityToReturnFromMapper)).thenReturn(teacherEntityToReturnFromSave);
        when(teacherMapper.dtoToTeacher(teacherDtoToSave)).thenReturn(teacherEntityToReturnFromMapper);


        Teacher returnedDtoFromService = teacherService.create(teacherDtoToSave);

        verify(teacherRepository, times(1)).save(teacherEntityToReturnFromMapper);
        assertNotNull(returnedDtoFromService);
    }

    @Test
    public void testUpdateTeacherById() throws IOException {
        TeacherDto teacherDtoToUpdate = new TeacherDto(1L, "John", "Smith", "byte");
        Teacher FromRepository = new Teacher(1L, "John", "Smith", new byte[1]);
        Teacher teacherToReturnFromMapper = new Teacher(1L, "John", "Smith", new byte[1]);
        Teacher teacherToReturnFromSave = new Teacher(1L, "John", "Smith", new byte[1]);

        when(teacherRepository.findById(any())).thenReturn(Optional.of(FromRepository));
        when(teacherMapper.dtoToTeacher(any())).thenReturn(teacherToReturnFromMapper);
        when(teacherRepository.save(any())).thenReturn(teacherToReturnFromSave);
        MultipartFile image = new MockMultipartFile("name", (byte[]) any());

        TeacherDto resultDtoFromUpdate = teacherService.update("1", teacherDtoToUpdate, image);

        verify(teacherRepository, times(1)).save(any(Teacher.class));
        assertNotNull(resultDtoFromUpdate);
        assertEquals(teacherDtoToUpdate, resultDtoFromUpdate);
    }
    @Test
    public void testDeleteTeacher() throws IOException {
        Teacher teacherEntityToReturnFromMapper = new Teacher(1L, "John", "Smith",  new byte[1]);

        when(teacherRepository.findById(anyLong())).thenReturn(Optional.of(teacherEntityToReturnFromMapper));
        teacherService.delete("1");
        verify(teacherRepository, times(1)).delete(teacherEntityToReturnFromMapper);
    }
}
