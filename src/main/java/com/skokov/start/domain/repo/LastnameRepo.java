package com.skokov.start.domain.repo;

import com.skokov.start.domain.entity.Lastname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LastnameRepo extends JpaRepository<Lastname,Long> {
    Lastname findByName(String name);
}