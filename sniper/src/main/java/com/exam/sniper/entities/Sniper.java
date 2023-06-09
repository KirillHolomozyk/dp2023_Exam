package com.exam.sniper.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "snipers")
public class Sniper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String unit;

    private Integer accuracy;

    private Float salary;
}