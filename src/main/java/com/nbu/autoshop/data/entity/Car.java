package com.nbu.autoshop.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "car")
@Where(clause = "is_deleted='false'")
public class Car extends BaseEntity {
    @UniqueElements
    private String plate;

    private String model;

    private int year;

    @OneToOne
    private Brand brand;

    @ManyToOne
    private Client owner;
}
