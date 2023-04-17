package com.brokenbrain.protitipo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

@NoArgsConstructor
public class GptService{

    private static String KEY = "sk-";
    @Getter @Setter
    private static String PROMPT = "";
    private static long MAX_TOKENS = 100;
    private static float TEMPERATURE = 1;
    private static String MODEL = "text-davinci-003";

    @Getter @Setter
    private Map<String, Object> map;

    public void gerarInput() {

        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost
                    ("https://api.openai.com/v1/completions");

            StringEntity entity = new StringEntity
                    ("{" +
                            "\"model\" : " +
                            "\"" +
                            MODEL +
                            "\"," +
                            "\"prompt\" : \"" +
                            PROMPT +
                            "\"," +
                            "\"max_tokens\" : " +
                            MAX_TOKENS +
                            "," +
                            "\"temperature\" : " +
                            TEMPERATURE +
                            "}");

            entity.setContentType("application/json");
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Authorization", "Bearer " + KEY);
            post.setEntity(entity);

            HttpResponse response = client.execute(post);

            if (response.getStatusLine().getStatusCode() != 201)
                System.out.println("HTTP Error: " + response.getStatusLine().getStatusCode());

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            String output;
            System.out.println("\n\nGPT Resposta: \n");

            ObjectMapper objectMapper = new ObjectMapper();

            while ( (output = reader.readLine()) != null ) {
                System.out.println(output);
                map = objectMapper.readValue(output, Map.class);
            }

            /*
            client.getConnectionManager().shutdown();
             */
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

}