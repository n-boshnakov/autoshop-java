package com.nbu.autoshop.view.model.update;

import com.nbu.autoshop.data.entity.Brand;
import com.nbu.autoshop.data.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateCarViewModel {
    private String plate;
    private String model;
    private int year;
    private Brand brand;
    private User owner;
}
