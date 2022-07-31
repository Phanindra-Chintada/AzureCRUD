package com.example.azureCrud.controller;

import com.azure.core.annotation.Get;
import com.example.azureCrud.model.StudentModel;
import com.example.azureCrud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/getAllStudents")
    public String getAllStudents(Model model){
        List<StudentModel> students = studentRepository.getAllStudents();
        model.addAttribute("students",students);
        return "students";
    }

    @GetMapping("/addNewStudent")
    public String addNewStudent(Model model){
        StudentModel student = new StudentModel();
        model.addAttribute("student",student);
        return "add_new_student";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student")StudentModel student){
        studentRepository.updateStudent(student);
        return "redirect:/students/getAllStudents";
    }

    @GetMapping("/update/{id}")
    public String updateStudentForm(@PathVariable String id, Model model){
        model.addAttribute("student",studentRepository.getStudentUsingId(id));
        return "update_student";
    }

    @PostMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable String id,
                                @ModelAttribute("student") StudentModel newStudent,
                                Model model){
        StudentModel student = studentRepository.getStudentUsingId(id);
        student.setFirstName(newStudent.getFirstName());
        student.setLastName(newStudent.getLastName());
        student.setEmail(newStudent.getEmail());

        studentRepository.updateStudent(student);
        return "redirect:/students/getAllStudents";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable String id) {
        StudentModel student = studentRepository.getStudentUsingId(id);
        studentRepository.deleteStudent(student);
        return "redirect:/students/getAllStudents";
    }
}
