package com.nbu.autoshop.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "repair")
@Where(clause = "is_deleted='false'")
public class Repair extends BaseEntity {
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate repairDate;

    @OneToOne
    private Car car;

    private Qualifications service;

    private int price;

    @ManyToOne
    private Client client;

    @ManyToOne
    private AutoShop autoshop;
}