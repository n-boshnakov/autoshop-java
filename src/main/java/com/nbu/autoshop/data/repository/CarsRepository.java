package com.nbu.autoshop.data.repository;

import com.nbu.autoshop.data.entity.Brand;
import com.nbu.autoshop.data.entity.Car;
import com.nbu.autoshop.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarsRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByOwner(User user);

    Car findCarById(long id);
}
