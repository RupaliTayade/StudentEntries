package com.SpringStudentEntry.studentEntries.service;

import com.SpringStudentEntry.studentEntries.dto.TeacherDto;
import com.SpringStudentEntry.studentEntries.entity.Teacher;
import com.SpringStudentEntry.studentEntries.mapper.TeacherMapper;
import com.SpringStudentEntry.studentEntries.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public List<TeacherDto> findAll() {
        return teacherMapper.teacherDtoList(teacherRepository.findAll());
    }

    @Override
    public Teacher create(TeacherDto teacher) {
        return teacherRepository.save(teacherMapper.dtoToTeacher(teacher));
    }

    @Override
    public TeacherDto findById(String id) {
        Teacher teacher = teacherRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Invalid teacher Id:" + id));
        return teacherMapper.teacherToDto(teacher);
    }

    @Override
    public TeacherDto update(String id, TeacherDto teacherDto, MultipartFile image) throws IOException {
        teacherDto.setId(Long.valueOf(id));
        if (image.getOriginalFilename() != null)
            teacherDto.setImg(java.util.Base64.getEncoder().encodeToString(image.getBytes()));
        teacherRepository.save(teacherMapper.dtoToTeacher(teacherDto));
        return teacherDto;
    }

    @Override
    public void delete(String id) {
        Teacher teacher = teacherRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        teacherRepository.delete(teacher);
    }
}
