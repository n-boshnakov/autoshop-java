package com.nbu.autoshop.view.model.create;

import com.nbu.autoshop.data.entity.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateAutoShopViewModel {
    private String name;
    private String address;
    private String ownerName;
    private String phoneNumber;
    private String email;
    private Brand brand;
}