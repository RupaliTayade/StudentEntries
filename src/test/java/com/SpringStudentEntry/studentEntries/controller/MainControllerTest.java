package com.SpringStudentEntry.studentEntries.controller;

import com.SpringStudentEntry.studentEntries.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {
    @MockBean
    private StudentService studentService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testStart() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(redirectedUrl("/students/list"));
    }
}
