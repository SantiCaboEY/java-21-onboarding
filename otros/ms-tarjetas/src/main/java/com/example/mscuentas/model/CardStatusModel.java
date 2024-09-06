package com.example.mscuentas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="estado_tarjeta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardStatusModel {
    @Column(name = "id")
    @Id
    Integer id;

    @Column(name = "detalle")
    String detail;
}
