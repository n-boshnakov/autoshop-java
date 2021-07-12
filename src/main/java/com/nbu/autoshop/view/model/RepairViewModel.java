package com.nbu.autoshop.view.model;

import com.nbu.autoshop.data.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RepairViewModel {
    private long id;
    private LocalDate repairDate;
    private Car car;
    private Qualifications service;
    private double price;
    private User client;
    private AutoShop autoshop;
}
