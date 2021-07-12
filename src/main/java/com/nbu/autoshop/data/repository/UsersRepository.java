package com.nbu.autoshop.data.repository;

import com.nbu.autoshop.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findById(long id);
}