package com.SpringStudentEntry.studentEntries.mapper;

import com.SpringStudentEntry.studentEntries.dto.TeacherDto;
import com.SpringStudentEntry.studentEntries.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TeacherMapperTest {
    @InjectMocks
    TeacherMapper teacherMapper;

    @Test
    void dtoToTeacher() {
        TeacherDto testEntity1 = new TeacherDto(1L, "John", "Smith", "byte");
        assertNotNull(teacherMapper.dtoToTeacher(testEntity1));
    }

    @Test
    void teacherToDto() {
        Teacher teacherEntityToReturnFromFindById = new Teacher(1L, "John", "Smith", new byte[1]);
        assertNotNull(teacherMapper.teacherToDto(teacherEntityToReturnFromFindById));
    }

    @Test
    void teacherDtoList() {
        Teacher testDto1 = new Teacher(1L, "John", "Smith", new byte[1]);
        Teacher testDto2 = new Teacher(3L, "Alex", "Noha", new byte[1]);

        List<Teacher> teacherDtoListToReturnFromMapper = new ArrayList<>();
        teacherDtoListToReturnFromMapper.add(testDto1);
        teacherDtoListToReturnFromMapper.add(testDto2);
        assertNotNull(teacherMapper.teacherDtoList(teacherDtoListToReturnFromMapper));
    }
}