package br.com.senai.s042.autoescolas042.application.core.service;

import br.com.senai.s042.autoescolas042.adapter.in.viacep.request.DadosConsultaCEP;
import br.com.senai.s042.autoescolas042.adapter.in.viacep.response.DadosDetalhamentoCEP;
import br.com.senai.s042.autoescolas042.exception.UnknownException;
import br.com.senai.s042.autoescolas042.exception.types.viacep.CEPNotFoundException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ViaCEPService {
    public DadosDetalhamentoCEP consultar(DadosConsultaCEP dados) {
        String url = "https://viacep.com.br/ws/" + dados.cep() + "/json/";

        try {
            String jsonResponse = Request.Get(url)
                    .connectTimeout(10000)
                    .socketTimeout(10000)
                    .execute()
                    .returnContent()
                    .asString();

            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            if (jsonObject.has("erro") && jsonObject.get("erro").getAsBoolean()) {
                throw new CEPNotFoundException("Este cep não existe!");
            }

            Gson gson = new Gson();

            return gson.fromJson(jsonObject, DadosDetalhamentoCEP.class);
        } catch (IOException e) {
            System.out.println("Erro de I/O: " + e.getMessage());
            throw new UnknownException("Erro: ", e);
        }
    }
}