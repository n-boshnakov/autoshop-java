package com.nbu.autoshop.data.repository;

import com.nbu.autoshop.data.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandsRepository extends JpaRepository<Brand, Long> {
        Brand findByBrand(String brand);
}
