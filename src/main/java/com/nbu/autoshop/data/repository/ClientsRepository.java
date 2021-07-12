package com.nbu.autoshop.data.repository;

import com.nbu.autoshop.data.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Client, Long> {
}
