package com.example.mscuentas.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "estado_cuenta")
@Getter
public class AccountStatusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "detalle")
    private String detail;
}
