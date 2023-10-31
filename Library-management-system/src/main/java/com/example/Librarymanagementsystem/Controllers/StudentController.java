package com.example.Librarymanagementsystem.Controllers;

import com.example.Librarymanagementsystem.CustomizedResponses.BasicStudentDetails;
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
    @GetMapping("/getStudent")
    public Student getStudent(@RequestParam("id")Integer studentId){
        return studentService.getStudent(studentId);
    }
    @GetMapping("/getBasicDetailsOfStudent")
    public BasicStudentDetails getBasicDetailsOfStudent(@RequestParam("id")int studentId){
        return studentService.getBasicDetailsOfStudent(studentId);
    }
}
