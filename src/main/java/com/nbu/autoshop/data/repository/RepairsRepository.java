package com.nbu.autoshop.data.repository;

import com.nbu.autoshop.data.entity.AutoShop;
import com.nbu.autoshop.data.entity.Car;
import com.nbu.autoshop.data.entity.Repair;
import com.nbu.autoshop.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairsRepository extends JpaRepository<Repair, Long> {
    List<Repair> findAllByClient(User user);

    List<Repair> findAllByAutoshop(AutoShop autoshop);

    List<Repair> findAllByCarAndAndAutoshop(Car car, AutoShop autoshop);
}
