package com.skokov.start.controllers;

import com.skokov.start.domain.entity.Student;
import com.skokov.start.domain.entity.User;
import com.skokov.start.domain.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
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
            @RequestParam String text,
            @RequestParam int groupp,
            Model model,
            @RequestParam("file") MultipartFile file
            ) throws IOException {
        String resultFileName = null;
        if (file!= null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadDir + "\\" + resultFileName));
        }
        final Student student = new Student(text,groupp,user,resultFileName);
        studentRepo.save(student);
        Iterable<Student> students=studentRepo.findAll();
        model.addAttribute("students",students);
        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Model model){
        Iterable<Student> students;
        if(filter!=null && !filter.isEmpty()){
            int flrt=Integer.parseInt(filter);
            students =studentRepo.findByGroupp(flrt);
        } else{
            students=studentRepo.findAll();
        }
        model.addAttribute("students",students);
        return "main";
    }

}

