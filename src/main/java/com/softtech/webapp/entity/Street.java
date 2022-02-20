package com.softtech.webapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Street {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private Neighborhood neighborhood;
}
