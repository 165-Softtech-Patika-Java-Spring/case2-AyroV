package com.softtech.webapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "country")
    private Country country;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "city")
    private City city;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "district")
    private District district;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "neighborhood")
    private Neighborhood neighborhood;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "street")
    private Street street;

    private Integer buildingNo;
    private Integer flatNo;
}
