package com.example.mspersonas.model;

import com.example.mspersonas.enums.PersonStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="usuarios")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
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
    private PersonStatus status;

    @Column(name = "tipo")
    private Integer type;
}
