package com.example.mscuentas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estado_cuenta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountStatusModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "detalle", length = 30 )
    private String detail;
}
