package com.brokenbrain.protitipo.model.entity.gpt;

import com.brokenbrain.protitipo.model.entity.usuario.UsuarioEntity;
import lombok.Getter;

public class PromptEntity {

    public String gerarPrompt(UsuarioEntity usuario){

        return "Gere uma rotina de 5 dias de" +
                "treino (listando cada dia) para uma pessoa com deficiencia ( " +
                usuario.getDescDeficiencia()+
                " ) em reabilitacao " +
                "pesando " +
                usuario.getPeso() +
                "Kgs, com " +
                usuario.getAltura() +
                "cm de altura e com " +
                usuario.getIdade() +
                " anos de idade. Com mínimo de tokens possivel";
    }

}
