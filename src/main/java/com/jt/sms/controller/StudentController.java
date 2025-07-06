package com.jt.sms.controller;
import com.jt.sms.dto.StudentDTO;
import com.jt.sms.entity.Student;
import com.jt.sms.service.StudentService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping
    public List<StudentDTO> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable String id) {
        var studentDTO = studentService.getStudentsById(id);
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO, student);
        return studentService.getStudentsById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        Student newStudent = new Student();
        BeanUtils.copyProperties(studentDTO, newStudent);
        return studentService.saveStudent(newStudent);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllStudent(@PathVariable String id) {
        studentService.deleteStudentById(id);
    }

    @PutMapping("/update/{id}")
    public StudentDTO updateStudent(@PathVariable String id, @RequestBody StudentDTO studentDTO){
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO, student);
        return studentService.updateStudentById(id, student);
    }
}
