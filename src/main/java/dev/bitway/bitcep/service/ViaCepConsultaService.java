package dev.bitway.bitcep.service;

import dev.bitway.bitcep.entity.dto.CepEntrada;
import dev.bitway.bitcep.entity.dto.apicep.ApiCepConsultaSaida;
import dev.bitway.bitcep.entity.dto.viacep.ViaCepConsultaSaida;
import dev.bitway.bitcep.utils.ApiCepUtils;
import dev.bitway.bitcep.utils.ViaCepUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepConsultaService {

    private ViaCepUtils viaCepUtils = new ViaCepUtils();
    private ApiCepUtils apiCepUtils = new ApiCepUtils();
    private ApiCepConsultaService apiCepConsultaService = new ApiCepConsultaService();

    public ResponseEntity<?> consultaCep(CepEntrada cepEntrada) {
        RestTemplate restTemplate = new RestTemplate();
        ViaCepConsultaSaida viaCepConsultaSaida = new ViaCepConsultaSaida();
        ApiCepConsultaSaida apiCepConsultaSaida = new ApiCepConsultaSaida();
        try {
            ResponseEntity<ViaCepConsultaSaida> response = restTemplate.getForEntity("https://viacep.com.br/ws/" +
                    cepEntrada.getCep() + "/json/", ViaCepConsultaSaida.class);

            viaCepConsultaSaida = response.getBody();

        } catch (Exception e) {

        } finally {
            if (viaCepConsultaSaida.getCep() == null) {
                apiCepConsultaSaida = apiCepConsultaService.consultaCep(cepEntrada);
            }
        }

        if (viaCepConsultaSaida.getCep() != null) {
            viaCepConsultaSaida.setCategoria(viaCepUtils.spliterator(viaCepConsultaSaida.getCep()));
            viaCepConsultaSaida.setOrigem("ViaCep");
            return ResponseEntity.status(200).body(viaCepUtils.trataRetorno(viaCepConsultaSaida));
        } else if (apiCepConsultaSaida.getCode() != null) {
            apiCepConsultaSaida.setOrigin("ApiCep");
            return ResponseEntity.status(200).body(apiCepUtils.trataRetorno(apiCepConsultaSaida));
        }
        return ResponseEntity.status(500).body("service" + ":" + "error");
    }
}