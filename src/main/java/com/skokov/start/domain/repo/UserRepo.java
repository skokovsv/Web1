package com.skokov.start.domain.repo;

import com.skokov.start.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,Long> {
    User findByUsername(String username);

    User findByActivationCode(String code);
}
