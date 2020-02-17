package com.SpringStudentEntry.StudentEntries.ControllerTest;

import com.SpringStudentEntry.StudentEntries.controller.TeacherController;
import com.SpringStudentEntry.StudentEntries.dto.StudentDto;
import com.SpringStudentEntry.StudentEntries.dto.TeacherDto;
import com.SpringStudentEntry.StudentEntries.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TeacherController.class)
public class TeacherControllerTest {
    @MockBean
    private TeacherService teacherService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShowForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/signup"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void testAddTeacher()throws Exception{
        TeacherDto teacher = new TeacherDto();
        StudentDto testDto1 = new StudentDto(1L, "John", "john@gmail.com", 2L, teacher, null);
        mockMvc.perform(MockMvcRequestBuilders.post("/teachers/add"))
                .andExpect(redirectedUrl("list"));}

    @Test
    public void testShowUpdateForm() throws Exception {
        TeacherDto testDto1 = new TeacherDto(1L, "John", "Smith", "byte");
        TeacherDto testDto2 = new TeacherDto(3L, "Alex", "Noha", "byte");

        List<TeacherDto> teacherDtoListToReturnFromService = new ArrayList<>();
        teacherDtoListToReturnFromService.add(testDto1);
        teacherDtoListToReturnFromService.add(testDto2);

        when(teacherService.findAll()).thenReturn(teacherDtoListToReturnFromService);

        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/list"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(teacherService, times(1)).findAll();
    }

    @Test
    public void testGetTeacherById() throws Exception {

        TeacherDto testDto1 = new TeacherDto(1L, "John", "Smith", "byte");

        when(teacherService.findById(any())).thenReturn(testDto1);
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/edit/1", 1))
                .andDo(print())
                .andExpect(status().isOk());

        verify(teacherService, times(1)).findById("1");
    }

    @Test
    public void testSaveTeacher() throws Exception {
        TeacherDto testDto1 = new TeacherDto(1L, "John", "Smith", "byte");

        when(teacherService.update(any(), any(), any())).thenReturn(testDto1);

        mockMvc.perform(MockMvcRequestBuilders.post("/teachers/update/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(teacherService, times(1)).update(any(), any(), any());
    }

    @Test
    public void testDeleteTeacher() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/delete/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(teacherService, times(1)).delete(eq("1"));
    }
}

