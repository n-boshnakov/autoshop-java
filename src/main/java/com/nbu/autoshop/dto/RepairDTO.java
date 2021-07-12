package com.nbu.autoshop.dto;

import com.nbu.autoshop.data.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RepairDTO {
    private long id;
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate repairDate;
    private Car car;
    private Qualifications service;
    private int price;
    private User client;
    private AutoShop autoshop;
}
