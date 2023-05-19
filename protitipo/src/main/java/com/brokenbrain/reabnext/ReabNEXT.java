package com.brokenbrain.reabnext;

import com.brokenbrain.reabnext.gpt.model.GPT;
import com.brokenbrain.reabnext.gpt.service.GptService;
import com.brokenbrain.reabnext.paciente.model.Paciente;
import com.brokenbrain.reabnext.treino.model.Treino;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class ReabNEXT {

    public static void main(String[] args) {

        // - Instanciando EntityManager e setando banco de dados
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("maria-db");
        EntityManager manager = factory.createEntityManager();

        // - Instanciando Paciente(usuario) com Nome, Data de nascimento, Peso, Altura e Descrição da deficiência
        var usuario = new Paciente();
        usuario.setNome("Alvaro");
        usuario.setDtNasc(LocalDate.now().minusYears(38));
        usuario.setPeso(58);
        usuario.setAltura(1.77f);
        usuario.setDescDeficiencia("Braco esquerdo amputado na altura do ombro");

        // - Instanciando Treino com quantidade de dias de treino, data de início e data de término(com base na qtd de dias de treino)
        // - e descrição da deficiência. Esses dados são usados no input da API da OpenAI
        var treino = new Treino();
        treino.setPaciente(usuario)
                .setQtdDias(5)
                .setDtInicio(LocalDateTime.now().plusDays(1))
                .setDtFim(LocalDateTime.now().plusDays(6))
                .setDescDeficiencia(usuario.getDescDeficiencia());

        // - Instanciando GPT e setando o treino.
        var gpt = new GPT(treino);

        // - getInputGptPrompt é o método que gera a String que será colocada como input na API da OpenAI para Gerar o treino
        String prompt = gpt.getInputGptPrompt();

        // - Instanciando service, passando o prompt de input e recebendo output através do método gerarTreino
        GptService service = new GptService();
        service.setPROMPT(prompt);
        service.gerarTreino();
        gpt.setOutputGpt( service.getOutputGptMap().toString() );
        System.out.println( service );

        // - Usando o manager(Instância de EntityManager) para fazer persistência dos dados
        manager.getTransaction().begin();
        manager.persist(gpt);
        manager.getTransaction().commit();
        manager.close();
        factory.close();


    }

}
