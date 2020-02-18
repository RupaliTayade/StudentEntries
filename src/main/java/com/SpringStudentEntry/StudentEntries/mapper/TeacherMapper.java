package com.SpringStudentEntry.StudentEntries.mapper;

import com.SpringStudentEntry.StudentEntries.dto.TeacherDto;
import com.SpringStudentEntry.StudentEntries.entity.Teacher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class TeacherMapper {
    public Teacher dtoToTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher.Builder().name(teacherDto.getName()).surname(teacherDto.getSurname()).build();
        if (teacherDto.getId() != null) teacher.setId(teacherDto.getId());
        if (teacherDto.getImg() != null) teacher.setImage(Base64.getDecoder().decode(teacherDto.getImg()));
        return teacher;
    }

    public TeacherDto teacherToDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setName(teacher.getName());
        teacherDto.setSurname(teacher.getSurname());
        if (teacher.getImage() != null) teacherDto.setImg(Base64.getEncoder().encodeToString(teacher.getImage()));
        return teacherDto;
    }

    public List<TeacherDto> teacherDtoList(List<Teacher> teachers) {
        List<TeacherDto> list = new ArrayList<>();
        for (Teacher teacher : teachers) {
            list.add(teacherToDto(teacher));
        }
        return list;
    }
}
