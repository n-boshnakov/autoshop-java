package com.nbu.autoshop.dto.update;

import com.nbu.autoshop.data.entity.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateCarDTO {
    private String plate;
    private String model;
    private Brand brand;
    private int year;
}
