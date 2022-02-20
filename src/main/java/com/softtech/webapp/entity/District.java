package com.softtech.webapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private City city;
}
