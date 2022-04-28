package dev.bitway.bitcep.service;

import dev.bitway.bitcep.entity.dto.CepEntrada;
import dev.bitway.bitcep.entity.dto.apicep.ApiCepConsultaSaida;
import dev.bitway.bitcep.entity.dto.awesomeapi.AwesomeApiConsultaSaida;
import dev.bitway.bitcep.entity.dto.opencep.OpenCepConsultaSaida;
import dev.bitway.bitcep.entity.dto.viacep.ViaCepConsultaSaida;
import dev.bitway.bitcep.service.callbacks.CepConsultaService;
import dev.bitway.bitcep.utils.ApiCepUtils;
import dev.bitway.bitcep.utils.ViaCepUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AwesomeApiConsultaService {

    private ViaCepUtils viaCepUtils = new ViaCepUtils();
    private ApiCepUtils apiCepUtils = new ApiCepUtils();
    private CepConsultaService cepConsultaService = new CepConsultaService();

    private RestTemplate restTemplate = new RestTemplate();

    private ViaCepConsultaSaida viaCepConsultaSaida = new ViaCepConsultaSaida();
    private ApiCepConsultaSaida apiCepConsultaSaida = new ApiCepConsultaSaida();
    private OpenCepConsultaSaida openCepConsultaSaida = new OpenCepConsultaSaida();
    private AwesomeApiConsultaSaida awesomeApiConsultaSaida = new AwesomeApiConsultaSaida();

    public ResponseEntity<?> consultaCep(CepEntrada cepEntrada) {
        try {
            ResponseEntity<AwesomeApiConsultaSaida> response = restTemplate.getForEntity("https://cep.awesomeapi.com.br/json/" +
                    cepEntrada.getCep(), AwesomeApiConsultaSaida.class);
            awesomeApiConsultaSaida = response.getBody();

        } catch (Exception e) {

        } finally {
            if (awesomeApiConsultaSaida.getCep() == null) {
                viaCepConsultaSaida = cepConsultaService.viaCepConsultaSaida(cepEntrada);
            }
            if (openCepConsultaSaida.getCep() == null) {
                openCepConsultaSaida = cepConsultaService.openCepConsultaSaida(cepEntrada);
            }
            if (apiCepConsultaSaida.getCode() == null) {
                apiCepConsultaSaida = cepConsultaService.apiCepConsultaSaida(cepEntrada);
            }
        }

        if (awesomeApiConsultaSaida.getCep() != null) {
            awesomeApiConsultaSaida.setOrigin("AwesomeAPI");
            return ResponseEntity.ok(awesomeApiConsultaSaida);
        } else if (viaCepConsultaSaida.getCep() != null) {
            viaCepConsultaSaida.setCategoria(viaCepUtils.spliterator(viaCepConsultaSaida.getCep()));
            viaCepConsultaSaida.setOrigem("ViaCep");
            return ResponseEntity.status(200).body(viaCepUtils.trataRetorno(viaCepConsultaSaida));
        } else if (openCepConsultaSaida.getCep() != null) {
            openCepConsultaSaida.setOrigem("OpenCep");
            return ResponseEntity.status(200).body(openCepConsultaSaida);
        } else if (apiCepConsultaSaida.getCode() != null) {
            apiCepConsultaSaida.setOrigin("ApiCep");
            return ResponseEntity.status(200).body(apiCepUtils.trataRetorno(apiCepConsultaSaida));
        }
        return ResponseEntity.status(500).body("service" + ":" + "error");
    }
}