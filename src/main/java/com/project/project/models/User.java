package com.project.project.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@ToString
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Long id;
    @Getter @Setter @Column(name = "nombre")
    private String name;
    @Getter @Setter @Column(name = "apellido")
    private String surName;
    @Getter @Setter @Column(name = "telefono")
    private String phone;
    @Getter @Setter @Column(name = "email")
    private String email;
    @Getter @Setter @Column(name = "contrasenia")
    private String password;


}
