package com.example.mscuentas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name="cuentas")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "numcue")
    private String id;

    @Column(name = "persnum")
    private Integer personNumber;

    @OneToOne
    @JoinColumn(name = "divisa", referencedColumnName = "id")
    private MoneySymbol moneySymbol;

    @OneToOne
    @JoinColumn(name = "estado", referencedColumnName = "id")
    private AccountStatusModel status;

    @Column(name = "saldo")
    private BigDecimal balance;
}
