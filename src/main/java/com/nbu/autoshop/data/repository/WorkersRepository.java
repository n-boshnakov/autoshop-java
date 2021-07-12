package com.nbu.autoshop.data.repository;

import com.nbu.autoshop.data.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkersRepository extends JpaRepository<Worker, Long> {
}
