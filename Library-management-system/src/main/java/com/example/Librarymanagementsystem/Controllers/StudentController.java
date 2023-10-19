package com.example.Librarymanagementsystem.Controllers;

import com.example.Librarymanagementsystem.Entities.Student;
import com.example.Librarymanagementsystem.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }
}
