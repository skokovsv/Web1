package com.skokov.start.domain.service;

import com.skokov.start.domain.entity.Firstname;
import com.skokov.start.domain.entity.Lastname;
import com.skokov.start.domain.entity.Secondname;
import com.skokov.start.domain.entity.Student;
import com.skokov.start.domain.repo.StudentRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    private StudentRepo studentRepo;

    public  StudentService(StudentRepo studentRepo){
        this.studentRepo=studentRepo;
    }
    public Student saveIntoStudent(Student student){
        if(student == null){
            return null;
        }
        Student fromDBstudent = findByFirstnameAndSecondnameAndLastname(
                student.getFirstname(),student.getSecondname(),student.getLastname());
        if(fromDBstudent == null){
            fromDBstudent = studentRepo.save(student);
        }
        return fromDBstudent;
    }
    public Student findByFirstnameAndSecondnameAndLastname(
            Firstname firstname, Secondname secondname, Lastname lastname){
        return studentRepo.findByFirstname_IdAndSecondnameIdAndLastnameId(
                firstname.getId(), secondname.getId(), lastname.getId());
    }
    public List<Student> findAll(){
        return studentRepo.findAll();
    }
    public List<Student> findByGroupp(int groupp){
        return (List<Student>) studentRepo.findByGroupp(groupp);
    }
}
