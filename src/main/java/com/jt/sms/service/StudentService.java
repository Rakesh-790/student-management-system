package com.jt.sms.service;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.jt.sms.dto.StudentDTO;
import com.jt.sms.entity.Student;
import com.jt.sms.exception.StudentNotFoundException;
import com.jt.sms.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<StudentDTO> getStudents() {
        var students = studentRepository.findAll();
        var studentDTOs = students.stream().map(student -> {
            var studentDTO = new StudentDTO();
            BeanUtils.copyProperties(student, studentDTO);
            return studentDTO;
        }).toList();
        return studentDTOs;
    }

    public StudentDTO getStudentsById(String id) {
        var existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
        var existingStudentDTO = new StudentDTO();
        BeanUtils.copyProperties(existingStudent, existingStudentDTO);
        return existingStudentDTO;
    }

    public StudentDTO saveStudent(Student newStudent) {
        var saveStudent =  studentRepository.save(newStudent);
        var studentDTO = new StudentDTO();
        BeanUtils.copyProperties(saveStudent, studentDTO);
        return studentDTO;
    }

    public void deleteStudentById(String id) {
        var existingStudent = getStudentsById(id);
        var student = new Student();
        BeanUtils.copyProperties(existingStudent, student);
        studentRepository.delete(student);   
    }

    public StudentDTO updateStudentById(String id, Student student) {
        getStudentsById(id);
        student.setStudentId(id);
        var updatedStudent = studentRepository.save(student);
        var studentDTO = new StudentDTO();  
        BeanUtils.copyProperties(updatedStudent, studentDTO);
        return studentDTO;
    }
}
