package com.example.Librarymanagementsystem.Services;

import com.example.Librarymanagementsystem.Entities.Student;
import com.example.Librarymanagementsystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private JavaMailSender mailSender;
    public String addStudent(Student student) {
        studentRepository.save(student);

        SimpleMailMessage mailMessage=new SimpleMailMessage();

        String body="Hi "+student.getStudentName()+" ! "+"You have successfully registered. You can issuing the books now";

        mailMessage.setFrom("hr5235577@gmail.com");
        mailMessage.setTo(student.getEmailId());
        mailMessage.setSubject("Welcome to Rahul's Library !!!");
        mailMessage.setText(body);

        mailSender.send(mailMessage);

        return "Student has been added successfully in the DB.";
    }
}
