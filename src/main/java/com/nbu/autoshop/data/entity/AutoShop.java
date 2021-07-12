package com.nbu.autoshop.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "autoshop")
@Where(clause = "is_deleted='false'")
public class AutoShop extends BaseEntity {
    private String name;

    private String address;

    private String ownerName;

    private String phoneNumber;

    private String email;

    @OneToOne
    private Brand brand;

    @OneToMany
    private List<Worker> workerList;

    @OneToMany
    private List<Repair> repairsList;
}
