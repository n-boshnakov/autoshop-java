package com.nbu.autoshop.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
@Where(clause = "is_deleted='false'")
public class Client extends User {
    @OneToMany
    private List<Repair> repairs;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Car> cars;
}