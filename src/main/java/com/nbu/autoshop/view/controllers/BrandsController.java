package com.nbu.autoshop.view.controllers;

import com.nbu.autoshop.dto.BrandDTO;
import com.nbu.autoshop.services.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@AllArgsConstructor
public class BrandsController {

    private final BrandService brandService;

    @GetMapping(value = "/api/brands")
    public List<BrandDTO> getBrands() {
        return brandService.getBrands();
    }

}