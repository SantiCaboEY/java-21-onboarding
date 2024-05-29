package com.example.mscuentas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "codigo_moneda")
public class MoneySymbol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_moneda")
    private String id;

    @Column(name = "pais")
    private String countryName;

    @Column(name = "simbolo")
    private String symbol;
}
