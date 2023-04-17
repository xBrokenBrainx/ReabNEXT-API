package com.brokenbrain.protitipo.model.entity.treino;

import com.brokenbrain.protitipo.model.entity.usuario.UsuarioEntity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_BB_Treino")
public class TreinoEntity {

    @Getter @Setter
    @Id
    @GeneratedValue(generator = "SQ_Treino", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SQ_Treino" , sequenceName = "SQ_Treino")
    @Column(name = "ID_Treino") private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH, CascadeType.PERSIST
    })
    @JoinColumn(name = "FK_Treino_Usuario",
                referencedColumnName = "ID_Usuario",
                foreignKey =
                    @ForeignKey(name = "FK_Treino_Usuario",value = ConstraintMode.CONSTRAINT)
    )
    @Getter @Setter private UsuarioEntity usuario;

    @Getter @Setter private String treino;


    @Override
    public String toString() {
        return "TreinoEntity{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", treino='" + treino + '\'' +
                '}';
    }
}
