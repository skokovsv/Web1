package com.skokov.start.domain.service;

import com.skokov.start.domain.entity.Role;
import com.skokov.start.domain.entity.User;
import com.skokov.start.domain.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    @Autowired myMailSender myMailSender;

    @Value("${hostname}")
    private String hostname;

    public boolean addUser(User user){
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if(userFromDB!=null){
            return false;
        }
        user.setActive(true);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        if(user.getEmail()!=null){
            String message = String.format(
                    "Приветствую Вас, %s! \n"+
                            "Для регистрации перейдите по следующей сслыке: http://%s/activate/%s",
                    user.getUsername(),
                    hostname,
                    user.getActivationCode()
            );
            myMailSender.send(user.getEmail(),"Код активации:",message);
        }
        return true;

    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if(user==null){
            System.out.println("RETURN FALSE");
            return false;
        }
        user.setActivationCode(null);
        userRepo.save(user);
        return true;
    }
}
