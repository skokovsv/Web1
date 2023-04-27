package com.skokov.start.domain.repo;

import com.skokov.start.domain.entity.Secondname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondnameRepo extends JpaRepository<Secondname,Long> {
    Secondname findByName(String name);
}
