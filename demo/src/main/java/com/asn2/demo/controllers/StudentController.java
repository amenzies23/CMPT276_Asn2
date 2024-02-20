package com.asn2.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asn2.demo.models.Student;
import com.asn2.demo.models.StudentRepository;

import jakarta.servlet.http.HttpServletResponse;



@Controller

public class StudentController {
    @Autowired
    private StudentRepository studentRepo;

    @GetMapping("/students/view")
    public String getAllStudents(Model model) {
        List<Student> students = studentRepo.findAll();
        model.addAttribute("st", students);
        return "students/showAll";
    }

    @PostMapping("/students/add")
    public String addStudent(@RequestParam Map<String, String> newstudent, HttpServletResponse response){
        System.out.println("ADD student");
        String newName = newstudent.get("name");
        String newMajor = newstudent.get("major");
        String newGPA = newstudent.get("gpa");
        int newHeight = Integer.parseInt(newstudent.get("height"));
        int newWeight = Integer.parseInt(newstudent.get("weight"));
        String newHairColor = newstudent.get("hair_color");
        studentRepo.save(new Student(newName, newMajor, newGPA, newHeight, newWeight, newHairColor));
        response.setStatus(201);
        return "students/addedStudent";
    }
    
    @DeleteMapping("/students/{id}/delete")
    public String deleteUser(@PathVariable("id") String id) { 
        studentRepo.deleteById(Integer.parseInt(id));
        // Delete the user in this method with the id.  
        return "students/deletedStudent";
 }
    
}

