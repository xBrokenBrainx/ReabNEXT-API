package com.brokenbrain.protitipo;

import com.brokenbrain.protitipo.gpt.model.GPT;
import com.brokenbrain.protitipo.gpt.service.GptService;
import com.brokenbrain.protitipo.paciente.model.Paciente;
import com.brokenbrain.protitipo.treino.model.Treino;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class ProtitipoApplication {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("maria-db");
        EntityManager manager = factory.createEntityManager();

        var usuario = new Paciente();
        usuario.setNascimento(LocalDate.now().minusYears(24));
        usuario.setPeso(53);
        usuario.setAltura(1.75f);
        usuario.setDescDeficiencia("Braco esquerdo amputado na altura do cotovelo");


        var treino = new Treino();
        treino.setPaciente(usuario)
                .setQuantidadeDeDias(5)
                .setInicio(LocalDateTime.now().plusDays(1))
                .setFim(LocalDateTime.now().plusDays(6))
                .setDescricao("Amputacao no braco esquerdo na altura do cotovelo");

        System.out.println(treino.toString());

        var gpt = new GPT();
        gpt.setTreino(treino);

        String prompt = gpt.getPrompt();

        GptService service = new GptService();
        service.setPROMPT(prompt);
        service.gerarInput();

        gpt.setOutput(service.getPromptMap().toString());

        System.out.println(service);


        manager.getTransaction().begin();
        manager.persist(gpt);
        manager.getTransaction().commit();
        manager.close();
        factory.close();


    }

}
