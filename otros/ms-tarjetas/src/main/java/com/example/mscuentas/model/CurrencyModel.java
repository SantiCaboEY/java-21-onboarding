package com.example.mscuentas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "codigo_moneda")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_moneda")
    private String id;

    @Column(name = "pais")
    private String countryName;

    @Column(name = "simbolo")
    private String symbol;
}
