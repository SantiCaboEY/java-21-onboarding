package com.example.mscuentas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name="cuentas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @Column(name =  "numcue")
    private String id;

    @Column(name = "persnum")
    private Integer personNumber;

    @ManyToOne
    @JoinColumn(name = "divisa", referencedColumnName = "cod_moneda")
    private CurrencyModel currencyModel;

    @ManyToOne
    @JoinColumn(name = "estado", referencedColumnName = "id")
    private AccountStatusModel status;

    @Column(name = "saldo")
    private BigDecimal balance;
}
