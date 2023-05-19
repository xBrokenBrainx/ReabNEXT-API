package com.brokenbrain.reabnext.gpt.model;

import jakarta.persistence.*;


@Entity
@Table(name = "TB_CHOICE")
public class Choice {
    @Id
    @GeneratedValue(generator = "SQ_CHOICE", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_CHOICE", sequenceName = "SQ_CHOICE")
    @Column(name = "ID_CHOICE")
    private Long id;
    private String texto;
    private String indx;
    private String logProbs;
    private String finishReason;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_GPT",
            referencedColumnName = "ID_GPT",
            foreignKey = @ForeignKey(name = "FK_GPT_CHOICE", value = ConstraintMode.CONSTRAINT)
    )
    private GPT gpt;


    public Long getId() {
        return id;
    }

    public Choice setId(Long id) {
        this.id = id;
        return this;
    }


    public String getLogProbs() {
        return logProbs;
    }

    public Choice setLogProbs(String logProbs) {
        this.logProbs = logProbs;
        return this;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public Choice setFinishReason(String finishReason) {
        this.finishReason = finishReason;
        return this;
    }

    public GPT getGpt() {
        return gpt;
    }

    public Choice setGpt(GPT gpt) {
        this.gpt = gpt;
        return this;
    }

    public Choice() {
    }


    public String getTexto() {
        return texto;
    }

    public Choice setTexto(String texto) {
        this.texto = texto;
        return this;
    }

    public String getIndx() {
        return indx;
    }

    public Choice setIndx(String indx) {
        this.indx = indx;
        return this;
    }

    public Choice(Long id, String texto, String indx, String logProbs, String finishReason, GPT gpt) {
        this.id = id;
        this.texto = texto;
        this.indx = indx;
        this.logProbs = logProbs;
        this.finishReason = finishReason;
        this.gpt = gpt;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", indx='" + indx + '\'' +
                ", logProbs='" + logProbs + '\'' +
                ", finishReason='" + finishReason + '\'' +
                '}';
    }
}
