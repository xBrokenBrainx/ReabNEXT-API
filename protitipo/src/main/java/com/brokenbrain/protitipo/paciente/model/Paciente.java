package com.brokenbrain.protitipo.paciente.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "TB_PACIENTE")
public class Paciente {

    @Id
    @GeneratedValue(generator = "SQ_PACIENTE", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_PACIENTE", sequenceName = "SQ_PACIENTE")
    @Column(name = "ID_PACIENTE")
    private Long id;

    @Column(name = "DESC_Deficiencia_Usuario")
    private String descDeficiencia;

    @Column(name = "Peso_Usuario")
    private float peso;

    @Column(name = "Altura_Usuario")
    private float altura;

    @Column(name = "DT_NASCIMENTO")
    private LocalDate nascimento;


    public Long getId() {
        return id;
    }

    public Paciente setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescDeficiencia() {
        return descDeficiencia;
    }

    public Paciente setDescDeficiencia(String descDeficiencia) {
        this.descDeficiencia = descDeficiencia;
        return this;
    }

    public float getPeso() {
        return peso;
    }

    public Paciente setPeso(float peso) {
        this.peso = peso;
        return this;
    }

    public float getAltura() {
        return altura;
    }

    public Paciente setAltura(float altura) {
        this.altura = altura;
        return this;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public Paciente setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        return this;
    }
}
