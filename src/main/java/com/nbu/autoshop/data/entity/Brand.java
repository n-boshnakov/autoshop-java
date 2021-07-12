package com.nbu.autoshop.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "brand")
@Where(clause = "is_deleted='false'")
public class Brand extends BaseEntity {

    private String brand;

}