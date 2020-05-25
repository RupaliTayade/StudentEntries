package com.SpringStudentEntry.studentEntries.repository;

import com.SpringStudentEntry.studentEntries.entity.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    List<Teacher> findAll();
}
