package com.skokov.start.controllers;

import com.skokov.start.domain.entity.*;
import com.skokov.start.domain.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private StudentRepo studentRepo;
    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping
    public String greeting(
            @RequestParam(name="name",required = false,defaultValue = "World") String name,
            Model model){
        model.addAttribute("name",name);
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model){
        Iterable<Student> students = studentRepo.findAll();
        model.addAttribute("students",students);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam("file")MultipartFile file,
            @Valid Student student,
            BindingResult bindingResult,
            Model model
            ) throws IOException {
        String resultFileName = null;
        if(bindingResult.hasErrors()){
            Map<String,String> errorsMap = ControllerUtils.getErrors( bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("student",student);
        } else{
            if (file!= null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                resultFileName = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadDir + "\\" + resultFileName));
            }
            student.setStudentuser(user);
            student.setAvatarname(resultFileName);
            studentRepo.save(student);
            student.setName("");
            student.setNamegroup("");
        }
        // Student student = new Student(text,groupp,user,resultFileName);
//        String[] names = text.split(" ");
//        Firstname firstname = new Firstname(names[0]);
//        Secondname secondname = new Secondname(names[1]);
//        Lastname lastname = new Lastname(names[2]);
//        final Student student = new Student(firstname,secondname,lastname,groupp,user,resultFileName);
//        studentRepo.save(student);
        Iterable<Student> students=studentRepo.findAll();
        model.addAttribute("students",students);
        model.addAttribute("student",null);
        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Model model){
        Iterable<Student> students;
        if(filter!=null && !filter.isEmpty()){
            //int flrt=Integer.parseInt(filter);
            students =studentRepo.findByNamegroup(filter);
        } else{
            students=studentRepo.findAll();
        }
        model.addAttribute("students",students);
        return "main";
    }

}

