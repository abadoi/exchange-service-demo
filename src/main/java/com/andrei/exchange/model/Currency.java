package com.andrei.exchange.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private double rate;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

}