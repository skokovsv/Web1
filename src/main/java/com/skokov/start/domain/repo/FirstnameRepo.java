package com.skokov.start.domain.repo;


import com.skokov.start.domain.entity.Firstname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirstnameRepo extends JpaRepository<Firstname,Long> {
    Firstname findByName(String name);
}
