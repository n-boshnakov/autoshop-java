package com.nbu.autoshop.data.repository;

import com.nbu.autoshop.data.entity.AutoShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AutoShopsRepository extends JpaRepository<AutoShop, Long> {
}
