package com.nbu.autoshop.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "worker")
@Where(clause = "is_deleted='false'")
public class Worker extends User {

    private Qualifications qualification;

    @ManyToOne
    private AutoShop worksFor;
}
