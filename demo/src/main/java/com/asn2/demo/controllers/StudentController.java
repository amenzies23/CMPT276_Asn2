package com.asn2.demo.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asn2.demo.models.Student;
import com.asn2.demo.models.StudentRepository;

import jakarta.servlet.http.HttpServletResponse;



@Controller

public class StudentController {
    @Autowired
    private StudentRepository studentRepo;

    //GET mapping to show the full list of students
    @GetMapping("/students/view")
    public String getAllStudents(Model model) {
        List<Student> students = studentRepo.findAll();
        model.addAttribute("st", students);
        return "students/showAll";
    }

    //GET mapping to return the info about a student based on their id (used for modal)
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {

        Optional<Student> student = studentRepo.findById(id);
        return student.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //GET mapping to show the deletedStudent page
    @GetMapping("/students/deletedStudent")
    public String showDeletedStudentPage() {
        return "deletedStudent";
    }
    //GET mapping to show the edit page for a student
    //This page will display all the info about the student, and the form on the edit page will invoke the next POST mapping
    @GetMapping("/students/{id}/edit")
    public String showEditStudentForm(@PathVariable("id") Integer id, Model model) {
    System.out.println("Fetching student for edit with ID: " + id);
    // Fetch the existing student from the database
    Student student = studentRepo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
    // Add the student to the model to pre-populate the form
    model.addAttribute("student", student);
    // Open the students/edit page
    return "students/edit";
}

    //After the edit form is invoked, this POST mapping will be invoked to post the updated student info
    @PostMapping("/students/{id}/update")
    public String updateStudent(@PathVariable("id") Integer id, @RequestParam Map<String, String> updatedStudent, RedirectAttributes redirectAttributes) {
    
    // Fetch the existing student from the database
    Student student = studentRepo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));

    // Update the student's properties with the new values
    student.setName(updatedStudent.get("name"));
    student.setMajor(updatedStudent.get("major"));
    student.setGpa(updatedStudent.get("gpa"));
    student.setHeight(Integer.parseInt(updatedStudent.get("height")));
    student.setWeight(Integer.parseInt(updatedStudent.get("weight")));
    student.setHair_color(updatedStudent.get("hair_color"));

    // Save the updated student back to the database
    studentRepo.save(student);
    return "students/editedStudent";
}

    //GET mapping for displaying the "add" page, this page will invoke the next POST mapping
    @GetMapping("/students/add")
    public String addStudentForm() {
        return "students/add";
    }

    //POST mapping to be invoked when the add page is submitted
    @PostMapping("/students/add")
    public String addStudent(@RequestParam Map<String, String> newstudent, HttpServletResponse response){
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
    
    //Delete mapping to delete a student by their id
    @DeleteMapping("/students/{id}/delete")
    public String deleteUser(@PathVariable("id") String id) { 
        studentRepo.deleteById(Integer.parseInt(id));  
        return "students/deletedStudent";
 }
    
}

