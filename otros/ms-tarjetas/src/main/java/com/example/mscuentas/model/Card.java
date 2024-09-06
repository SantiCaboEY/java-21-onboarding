package com.example.mscuentas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tarjetas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @Column(name = "numtarj")
    private String number;

    @Column(name = "numcue")
    private Integer accountNumber;

    @Column(name = "f_vencimiento")
    private String expiryDate;

    @Column(name = "pin")
    private Integer pin;

    @ManyToOne
    @JoinColumn(name = "estado", referencedColumnName = "id")
    private CardStatusModel status;

    @Column(name = "f_emision")
    private String issueDate;

    @Column(name = "tipo")
    private String type;
}
