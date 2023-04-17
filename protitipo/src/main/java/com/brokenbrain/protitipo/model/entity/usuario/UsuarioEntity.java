package com.brokenbrain.protitipo.model.entity.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_BB_Usuario")
public class UsuarioEntity {

    @Getter @Setter
    @Id
    @GeneratedValue(generator = "SQ_Usuario", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SQ_Usuario", sequenceName = "SQ_Usuario")
    @Column(name = "ID_Usuario") private Long id;

    @Getter @Setter
    @Column(name = "DESC_Deficiencia_Usuario") private String descDeficiencia;

    @Getter @Setter
    @Column(name = "Peso_Usuario") private float peso;

    @Getter @Setter
    @Column(name = "Altura_Usuario") private int altura;

    @Getter @Setter
    @Column(name = "Idade_Usuario") private int idade;

    @Override
    public String toString() {
        return "UsuarioEntity{" +
                "id=" + id +
                ", descDeficiencia='" + descDeficiencia + '\'' +
                ", peso=" + peso +
                ", altura=" + altura +
                ", idade=" + idade +
                '}';
    }
}
