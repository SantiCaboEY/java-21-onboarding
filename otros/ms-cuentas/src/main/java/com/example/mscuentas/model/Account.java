package com.example.mscuentas.model;

import com.example.mscuentas.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="cuentas")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "persnum")
    private Integer id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellido")
    private String lastName;

    @Column(name = "dni")
    private String dni;

    @Column(name = "estado", columnDefinition = "smallInt")
    private AccountStatus status;

    @Column(name = "tipo")
    private Integer type;
}
