package com.skokov.start.domain.service;

import com.skokov.start.domain.entity.Lastname;
import com.skokov.start.domain.repo.LastnameRepo;
import org.springframework.stereotype.Service;

@Service
public class LastnameService {
    private LastnameRepo lastnameRepo;

    public LastnameService(LastnameRepo lastnameRepo){
        this.lastnameRepo = lastnameRepo;
    }

    public Lastname saveIntoLastname(Lastname lastname){
        if(lastname==null){
            return null;
        }
        Lastname fromDBlastname = lastnameRepo.findByName(lastname.getName());
        if(fromDBlastname==null){
            fromDBlastname=lastnameRepo.save(lastname);
        }
        return fromDBlastname;

    }
    public Lastname findLastnameByFamily(String family){
        return lastnameRepo.findByName(family);
    }
}
