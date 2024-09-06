package com.example.mscuentas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardStatementsId {

    @Column(name = "id")
    private Integer id;

    @Column(name = "resumeMongoID")
    private String referenceId;
}
