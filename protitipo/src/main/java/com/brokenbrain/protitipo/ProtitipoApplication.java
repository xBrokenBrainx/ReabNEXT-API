package com.brokenbrain.protitipo;

import com.brokenbrain.protitipo.model.entity.gpt.PromptEntity;
import com.brokenbrain.protitipo.model.entity.usuario.UsuarioEntity;
import com.brokenbrain.protitipo.service.GptService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class ProtitipoApplication {

    public static void main(String[] args) {


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");
        EntityManager manager = factory.createEntityManager();

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setIdade(24);
        usuario.setPeso(53);
        usuario.setAltura(175);
        usuario.setDescDeficiencia("Braco esquerdo amputado na altura do cotovelo");

        manager.getTransaction().begin();
        manager.persist(usuario);
        manager.getTransaction().commit();
        manager.close();

        PromptEntity promptEntity = new PromptEntity();
        String prompt = promptEntity.gerarPrompt(usuario);

        GptService gpt = new GptService();
        gpt.setPROMPT(prompt);
        gpt.gerarInput();

    }

}
