package dev.bitway.bitcep.service;

import dev.bitway.bitcep.entity.dto.CepEntrada;
import dev.bitway.bitcep.entity.dto.apicep.ApiCepConsultaSaida;
import dev.bitway.bitcep.entity.dto.awesomeapi.AwesomeApiConsultaSaida;
import dev.bitway.bitcep.entity.dto.opencep.OpenCepConsultaSaida;
import dev.bitway.bitcep.entity.dto.viacep.ViaCepConsultaSaida;
import dev.bitway.bitcep.service.callbacks.CepConsultaService;
import dev.bitway.bitcep.utils.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
public class AwesomeApiConsultaService {

    private CepConsultaService cepConsultaService = new CepConsultaService();

    private ViaCepUtils viaCepUtils = new ViaCepUtils();
    private ApiCepUtils apiCepUtils = new ApiCepUtils();
    private OpenCepUtils openCepUtils = new OpenCepUtils();
    private AwesomeApiCepUtils awesomeApiCepUtils = new AwesomeApiCepUtils();
    private Utils utils = new Utils();
    private RestTemplate restTemplate = new RestTemplate();
    private ViaCepConsultaSaida viaCepConsultaSaida = new ViaCepConsultaSaida();
    private ApiCepConsultaSaida apiCepConsultaSaida = new ApiCepConsultaSaida();
    private OpenCepConsultaSaida openCepConsultaSaida = new OpenCepConsultaSaida();
    private AwesomeApiConsultaSaida awesomeApiConsultaSaida = new AwesomeApiConsultaSaida();


    public ResponseEntity<?> consultaCep(CepEntrada cepEntrada) {
        int status = 0;
        try {
            ResponseEntity<AwesomeApiConsultaSaida> response = restTemplate.getForEntity("https://cep.awesomeapi.com.br/json/" +
                    cepEntrada.getCep(), AwesomeApiConsultaSaida.class);
            awesomeApiConsultaSaida = response.getBody();
            status = response.getStatusCodeValue();

        } catch (Exception e) {
            e.printStackTrace();
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
        if (awesomeApiConsultaSaida.getCep() != null && status != 0) {
            log.info("AwesomeApi utilizado - CEP: " + awesomeApiConsultaSaida.getCep() + " - HTTP STATUS - " + status);
            return ResponseEntity.status(200).body(awesomeApiCepUtils.toSingleObject(awesomeApiConsultaSaida));
        } else if (viaCepConsultaSaida.getCep() != null) {
            log.info("ViaCep utilizado - CEP: " + awesomeApiConsultaSaida.getCep() + " - HTTP STATUS - " + status);
            return ResponseEntity.status(200).body(viaCepUtils.toSingleObject(viaCepConsultaSaida));
        } else if (openCepConsultaSaida.getCep() != null) {
            log.info("OpenCep utilizado - CEP: " + awesomeApiConsultaSaida.getCep() + " - HTTP STATUS - " + status);
            return ResponseEntity.status(200).body(openCepUtils.toSingleObject(openCepConsultaSaida));
        } else if (apiCepConsultaSaida.getCode() != null) {
            log.info("ApiCep utilizado - CEP: " + awesomeApiConsultaSaida.getCep() + " - HTTP STATUS - " + status);
            apiCepConsultaSaida.setOrigin("ApiCep");
            return ResponseEntity.status(200).body(apiCepUtils.toSingleObject(apiCepConsultaSaida));
        }
        return ResponseEntity.status(500).body("{\"service\"" + ":" + "\"error\"}");
    }
}