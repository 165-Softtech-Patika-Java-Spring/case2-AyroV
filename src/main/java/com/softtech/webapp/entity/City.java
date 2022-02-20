package com.softtech.webapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int plateNo;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private Country country;
}
