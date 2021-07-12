package com.nbu.autoshop.data.repository;

import com.nbu.autoshop.data.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RolesRepository extends JpaRepository<UserRole, Long> {
    Set<UserRole> findAllByAuthority(String name);

    UserRole findByAuthorityEquals(String name);
}