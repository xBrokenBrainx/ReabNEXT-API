package com.brokenbrain.reabnext.treino.model;

import com.brokenbrain.reabnext.paciente.model.Paciente;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "TB_Treino")
public class Treino {

    @Id
    @GeneratedValue(generator = "SQ_TREINO", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SQ_TREINO", sequenceName = "SQ_TREINO")
    @Column(name = "ID_Treino")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_PACIENTE",
            referencedColumnName = "ID_PACIENTE",
            foreignKey = @ForeignKey(name = "FK_TREINO_PACIENTE", value = ConstraintMode.CONSTRAINT)
    )
    private Paciente paciente;

    @Column(name = "DS_DEFICIENCIA")
    private String descDeficiencia;

    @Column(name = "DT_GERACAO")
    private
    LocalDateTime dtGeracao;

    @Column(name = "NR_DIAS")
    int qtdDias;

    @Column(name = "DT_INICIO")
    private
    LocalDateTime dtInicio;

    @Column(name = "DT_FIM")
    private
    LocalDateTime dtFim;


    public int getQtdDias() {
        return qtdDias;
    }

    public Treino setQtdDias(int quantidadeDeDias) {
        this.qtdDias = quantidadeDeDias;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Treino setId(Long id) {
        this.id = id;
        return this;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Treino setPaciente(Paciente paciente) {
        this.paciente = paciente;
        return this;
    }

    public String getDescDeficiencia() {
        return descDeficiencia;
    }

    public Treino setDescDeficiencia(String descricao) {
        this.descDeficiencia = descricao;
        return this;
    }

    public LocalDateTime getDtGeracao() {
        return dtGeracao;
    }

    public Treino setDtGeracao(LocalDateTime geracao) {
        this.dtGeracao = geracao;
        return this;
    }

    public LocalDateTime getDtInicio() {
        return dtInicio;
    }

    public Treino setDtInicio(LocalDateTime inicio) {
        this.dtInicio = inicio;
        return this;
    }

    public LocalDateTime getDtFim() {
        return dtFim;
    }

    public Treino setDtFim(LocalDateTime fim) {
        this.dtFim = fim;
        return this;
    }


}
