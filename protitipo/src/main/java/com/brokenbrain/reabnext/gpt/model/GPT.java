package com.brokenbrain.reabnext.gpt.model;

import com.brokenbrain.reabnext.treino.model.Treino;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "TB_GPT")
public class GPT {

    @Id
    @GeneratedValue(generator = "SQ_GPT", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_GPT", sequenceName = "SQ_GPT")
    @Column(name = "ID_GPT")
    Long idGpt;
    @Column(name = "API_ID_GPT")
    String id;

    @Column(name = "OBJ_GPT")
    String object;

    @Column(name = "DT_CRIACAO_GPT")
    LocalDate created;

    @Column(name = "NM_MODEL_GPT")
    String model;

    // - Relacionamento de Muitos para Um (N:1) De: Treino(N) para GPT(1)
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_TREINO",
            referencedColumnName = "ID_TREINO",
            foreignKey = @ForeignKey(name = "FK_GPT_TREINO", value = ConstraintMode.CONSTRAINT)
    )
    private Treino treino;

    // - Relacionamento de Um para Muitos (1:N) De: Choices(N) para GPT(1)
    // - Esse relacionamento existe por conta da API da OpenAI poder gerar multiplos outputs para um input
    @OneToMany(mappedBy = "gpt", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Choice> choices = new LinkedHashSet<>();

    @Embedded
    private Usage usage;

    @Column(name = "INPUT_GPT")
    private final String inputGpt = """
            Gere uma lista com uma rotina de %s dias de treino de fiseoterapia (cada dia sendo um item da lista) a partir de %s para uma pessoa com deficiencia ( %s ) em reabilitacao, pesando %,.0f Kg, com %,.2f metros de altura e com %s anos de idade.""";

    @Column(name = "PROMPT_GPT")
    private String inputGptPrompt;
    @Column(name = "output", columnDefinition = "TEXT")
    private String outputGpt;
    
    public GPT() {
    }

    public GPT(Treino treino){
        this.treino = treino;
    }

    public GPT(String id, String object, LocalDate created, String model, Treino treino, Set<Choice> choices) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.treino = treino;
        this.choices = choices;
    }

    // - getInputGptPrompt é o método que gera a String que será colocada como input na API da OpenAI para Gerar o treino
    public String getInputGptPrompt() {
        String stringFormatada = String.format(inputGpt,
                treino.getQtdDias(),
                treino.getDtInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                treino.getPaciente().getDescDeficiencia(),
                treino.getPaciente().getPeso(),
                treino.getPaciente().getAltura(),
                Year.now().minusYears(treino.getPaciente().getDtNasc().getYear()));
        return stringFormatada;
    }

    public String getOutputGpt() {
        return outputGpt;
    }

    public GPT setOutputGpt(String output) {
        this.outputGpt = output;
        return this;
    }

    public Treino getTreino() {
        return treino;
    }

    public GPT setTreino(Treino treino) {
        this.treino = treino;
        return this;
    }

    public Long getIdGpt() {
        return idGpt;
    }

    public GPT setIdGpt(Long code) {
        this.idGpt = code;
        return this;
    }

    public String getId() {
        return id;
    }

    public GPT setId(String id) {
        this.id = id;
        return this;
    }

    public String getObject() {
        return object;
    }

    public GPT setObject(String object) {
        this.object = object;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public GPT setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public String getModel() {
        return model;
    }

    public GPT setModel(String model) {
        this.model = model;
        return this;
    }

    public Set<Choice> getChoices() {
        return choices;
    }

    public GPT setChoices(Set<Choice> choices) {
        this.choices = choices;
        return this;
    }

    @Override
    public String toString() {
        return "GPT{" +
                "code=" + idGpt +
                ", id='" + id + '\'' +
                ", object='" + object + '\'' +
                ", created=" + created +
                ", model='" + model + '\'' +
                ", treino=" + treino +
                ", choices=" + choices +
                ", usage=" + usage +
                ", v='" + inputGpt + '\'' +
                ", prompt='" + inputGptPrompt + '\'' +
                ", output='" + outputGpt + '\'' +
                '}';
    }
}
