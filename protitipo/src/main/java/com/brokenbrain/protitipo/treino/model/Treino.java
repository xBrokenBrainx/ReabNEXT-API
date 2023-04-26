package com.brokenbrain.protitipo.treino.model;

import com.brokenbrain.protitipo.paciente.model.Paciente;
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

    @Column(name = "DS_TREINO")
    private String descricao;

    @Column(name = "DT_GERACAO")
    private
    LocalDateTime geracao;

    @Column(name = "NR_DIAS")
    int quantidadeDeDias;

    @Column(name = "DT_INICIO")
    private
    LocalDateTime inicio;

    @Column(name = "DT_FIM")
    private
    LocalDateTime fim;


    public int getQuantidadeDeDias() {
        return quantidadeDeDias;
    }

    public Treino setQuantidadeDeDias(int quantidadeDeDias) {
        this.quantidadeDeDias = quantidadeDeDias;
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

    public String getDescricao() {
        return descricao;
    }

    public Treino setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public LocalDateTime getGeracao() {
        return geracao;
    }

    public Treino setGeracao(LocalDateTime geracao) {
        this.geracao = geracao;
        return this;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public Treino setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
        return this;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public Treino setFim(LocalDateTime fim) {
        this.fim = fim;
        return this;
    }


}
