package com.brokenbrain.protitipo.model.entity.gpt;

import com.brokenbrain.protitipo.model.entity.usuario.UsuarioEntity;
import com.brokenbrain.protitipo.service.GptService;

public class PromptEntity {

    public String gerarPrompt(UsuarioEntity usuario){

        return "Gere uma lista com uma rotina de 5 dias de" +
                "treino(cada dia sendo um item da lista) para uma pessoa com deficiencia ( " +
                usuario.getDescDeficiencia()+
                " ) em reabilitacao " +
                "pesando " +
                usuario.getPeso() +
                "Kgs, com " +
                usuario.getAltura() +
                "cm de altura e com " +
                usuario.getIdade() +
                " anos de idade.";
    }

    public String tratamentoOutput(GptService service){
        return "String tratada: \n" +
                service
                .getPromptMap()
                .get("choices")
                .toString()
                .replace("[{text=.","")
                .replace("[{text=","")
                .replace(", index=0, logprobs=null, finish_reason=length}]", "")
                .replace("index=0, logprobs=null, finish_reason=length}]", "")
                .replace("., index=0, logprobs=null, finish_reason=length}]", ".");

    }

}
