package com.nbu.autoshop.view.model.create;

import com.nbu.autoshop.data.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CreateRepairViewModel {
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Future(message="The date has to be in the future!")
    private LocalDate repairDate;
    private Car car;
    private Qualifications service;
    private double price;
    private User client;
    private AutoShop autoshop;
}
