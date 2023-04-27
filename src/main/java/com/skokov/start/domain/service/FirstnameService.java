package com.skokov.start.domain.service;


import com.skokov.start.domain.entity.Firstname;
import com.skokov.start.domain.repo.FirstnameRepo;
import org.springframework.stereotype.Service;

@Service
public class FirstnameService {
    private FirstnameRepo firstnameRepo;

    public FirstnameService(FirstnameRepo firstnameRepo){
        this.firstnameRepo = firstnameRepo;
    }

    public Firstname saveIntoFirstname(Firstname firstname){
        if(firstname==null){
            return null;
        }
        Firstname fromDBfirstname = findFirstnameByName(firstname.getName());
        if(fromDBfirstname==null){
            fromDBfirstname = firstnameRepo.save(firstname);
        }
        return fromDBfirstname;
    }

    public Firstname findFirstnameByName(String name){
        return firstnameRepo.findByName(name);
    }
}
