package com.brokenbrain.protitipo.gpt.model;

import com.brokenbrain.protitipo.paciente.model.Paciente;
import com.brokenbrain.protitipo.treino.model.Treino;
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
    Long code;

    String id;

    String object;

    LocalDate created;

    String model;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_TREINO",
            referencedColumnName = "ID_TREINO",
            foreignKey = @ForeignKey(name = "FK_GPT_TREINO", value = ConstraintMode.CONSTRAINT)
    )
    private Treino treino;

    @OneToMany(mappedBy = "gpt", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Choice> choices = new LinkedHashSet<>();

    @Embedded
    private Usage usage;

    private final String v = """
            Gere uma lista com uma rotina de %s dias de treino de fiseoterapia (cada dia sendo um item da lista) a partir de %s para uma pessoa com deficiencia ( %s ) em reabilitacao, pesando %,.0f Kg, com %,.2f metros de altura e com %s anos de idade.""";

    private String prompt = "";
    @Column(name = "output", columnDefinition = "TEXT")
    private String output;


    public GPT() {
    }

    public GPT(String id, String object, LocalDate created, String model, Treino treino, Set<Choice> choices) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.treino = treino;
        this.choices = choices;
    }

    public GPT(Treino treino) {
        this.treino = treino;
    }

    public String getPrompt() {
        String p = String.format(v, treino.getQuantidadeDeDias(), treino.getInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), treino.getPaciente().getDescDeficiencia(), treino.getPaciente().getPeso(), treino.getPaciente().getAltura(), Year.now().minusYears(treino.getPaciente().getNascimento().getYear()));
        return p;
    }

    public String getOutput() {
        return output;
    }

    public GPT setOutput(String output) {
        this.output = output;
        return this;
    }

    public Treino getTreino() {
        return treino;
    }

    public GPT setTreino(Treino treino) {
        this.treino = treino;
        return this;
    }

    public Long getCode() {
        return code;
    }

    public GPT setCode(Long code) {
        this.code = code;
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
                "code=" + code +
                ", id='" + id + '\'' +
                ", object='" + object + '\'' +
                ", created=" + created +
                ", model='" + model + '\'' +
                ", treino=" + treino +
                ", choices=" + choices +
                ", usage=" + usage +
                ", v='" + v + '\'' +
                ", prompt='" + prompt + '\'' +
                ", output='" + output + '\'' +
                '}';
    }
}
