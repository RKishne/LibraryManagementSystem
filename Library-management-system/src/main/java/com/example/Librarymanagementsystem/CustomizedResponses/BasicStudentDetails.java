package com.example.Librarymanagementsystem.CustomizedResponses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//DTO stands for data transfer object
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasicStudentDetails {

    private String studentName;
    private int age;
    private String MobNo;
}
