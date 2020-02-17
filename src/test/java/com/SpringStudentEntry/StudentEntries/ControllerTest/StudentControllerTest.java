package com.SpringStudentEntry.StudentEntries.ControllerTest;

import com.SpringStudentEntry.StudentEntries.controller.StudentController;
import com.SpringStudentEntry.StudentEntries.dto.StudentDto;
import com.SpringStudentEntry.StudentEntries.dto.TeacherDto;
import com.SpringStudentEntry.StudentEntries.service.StudentService;
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
@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @MockBean
    private StudentService studentService;
    @MockBean
    TeacherService teacherService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testShowForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students/signup"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void testAddStudent()throws Exception{
        TeacherDto teacher = new TeacherDto();
        StudentDto testDto1 = new StudentDto(1L, "John", "john@gmail.com", 2L, teacher, null);
        mockMvc.perform(MockMvcRequestBuilders.post("/students/add"))
                .andExpect(redirectedUrl("list"));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/students/add"))
//                .andDo(print())
//                .andExpect(status().isOk());


        verify(studentService, times(1)).create(any());
    }

    @Test
    public void testShowUpdateForm() throws Exception {
        TeacherDto teacher = new TeacherDto();
        StudentDto testDto1 = new StudentDto(1L, "John", "john@gmail.com", 2L, teacher, "byte");
        StudentDto testDto2 = new StudentDto(3L, "Alex", "alex@gmail.com", 4L, teacher, "byte");

        List<StudentDto> studentDtoListToReturnFromService = new ArrayList<>();
        studentDtoListToReturnFromService.add(testDto1);
        studentDtoListToReturnFromService.add(testDto2);

        when(studentService.findAll()).thenReturn(studentDtoListToReturnFromService);

        mockMvc.perform(MockMvcRequestBuilders.get("/students/list"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(studentService, times(1)).findAll();
    }

    @Test
    public void testGetStudentById() throws Exception {
        TeacherDto teacher = new TeacherDto();

        StudentDto testDto1 = new StudentDto(1L, "John", "john@gmail.com", 2L, teacher, null);

        when(studentService.findById(any())).thenReturn(testDto1);
        mockMvc.perform(MockMvcRequestBuilders.get("/students/edit/1", 1))
                .andDo(print())
                .andExpect(status().isOk());

        verify(studentService, times(1)).findById("1");
    }

    @Test
    public void testSaveStudent() throws Exception {
        TeacherDto teacher = new TeacherDto();
        StudentDto testDto1 = new StudentDto(1L, "John", "john@gmail.com", 2L, teacher, null);

        when(studentService.update(any(), any(), any(), any())).thenReturn(testDto1);

        mockMvc.perform(MockMvcRequestBuilders.post("/students/update/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(studentService, times(1)).update(any(), any(), any(), any());
    }

    @Test
    public void testDeleteStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students/delete/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(studentService, times(1)).delete(eq("1"));

    }
}
