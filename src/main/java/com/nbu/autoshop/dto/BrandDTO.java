package com.nbu.autoshop.dto;

import com.nbu.autoshop.data.entity.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BrandDTO {
    private long id;
    private String brand;
}
