package com.example.mscuentas.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name="resumen_emitidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardStatements {
    @EmbeddedId
    private CardStatementsId bookId;

    @Column(name = "total_pesos")
    private BigDecimal balancePeso;

    @Column(name = "total_dolares")
    private BigDecimal balanceDollar;

    @Column(name = "fecha_vencimiento")
    private String dueDate;
}
