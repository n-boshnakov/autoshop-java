package com.nbu.autoshop.services.implementations;

import com.nbu.autoshop.data.entity.AutoShop;
import com.nbu.autoshop.data.entity.Brand;
import com.nbu.autoshop.data.repository.BrandsRepository;
import com.nbu.autoshop.dto.AutoShopDTO;
import com.nbu.autoshop.dto.BrandDTO;
import com.nbu.autoshop.services.BrandService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandsRepository brandsRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<BrandDTO> getBrands() {
        return brandsRepository.findAll().stream()
                .map(this::convertToBrandDTO)
                .collect(Collectors.toList());
    }


    private BrandDTO convertToBrandDTO(Brand brand) {
        return modelMapper.map(brand, BrandDTO.class);
    }
}
