package com.brokenbrain.reabnext.paciente.model;

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

    @Column(name = "NM_Paciente")
    private String nome;

    @Column(name = "DS_DEFICIENCIA")
    private String descDeficiencia;

    @Column(name = "PESO_Paciente")
    private float peso;

    @Column(name = "ALTURA_Paciente")
    private float altura;

    @Column(name = "DT_NASC_Paciente")
    private LocalDate dtNasc;

    public Long getId() {
        return id;
    }

    public Paciente setId(Long id) {
        this.id = id;
        return this;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
    public LocalDate getDtNasc() {
        return dtNasc;
    }
    public Paciente setDtNasc(LocalDate nascimento) {
        this.dtNasc = nascimento;
        return this;
    }

}
