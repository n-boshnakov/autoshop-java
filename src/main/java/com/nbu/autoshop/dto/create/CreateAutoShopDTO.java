package com.nbu.autoshop.dto.create;

import com.nbu.autoshop.data.entity.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CreateAutoShopDTO {
    @NotBlank
    @Size(min = 5, max = 20, message="Min 5, Max 20")
    private String name;
    @NotBlank
    @Size(min = 5, max = 100, message="Min 5, Max 20")
    private String address;
    @NotBlank
    @Size(min = 5, max = 20, message="Min 5, Max 20")
    private String ownerName;
    @NotBlank
    @Size(min = 5, max = 20, message="Min 5, Max 20")
    private String phoneNumber;
    @NotBlank
    @Size(min = 5, max = 20, message="Min 5, Max 20")
    private String email;
    private Brand brand;
}
