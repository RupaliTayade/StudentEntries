package com.SpringStudentEntry.StudentEntries.repository;

import com.SpringStudentEntry.StudentEntries.entity.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    List<Teacher> findAll();
}
