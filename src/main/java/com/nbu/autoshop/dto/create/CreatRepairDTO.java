package com.nbu.autoshop.dto.create;

import com.nbu.autoshop.data.entity.AutoShop;
import com.nbu.autoshop.data.entity.Car;
import com.nbu.autoshop.data.entity.Client;
import com.nbu.autoshop.data.entity.Qualifications;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CreatRepairDTO {
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate repairDate;
    private Car car;
    private Qualifications service;
    private double price;
    private Client client;
    private AutoShop autoshop;
}
