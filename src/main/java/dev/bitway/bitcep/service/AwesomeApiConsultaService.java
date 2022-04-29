package dev.bitway.bitcep.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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

    public ResponseEntity<?> consultaCep(CepEntrada cepEntrada) {
        ViaCepConsultaSaida viaCepConsultaSaida = new ViaCepConsultaSaida();
        ApiCepConsultaSaida apiCepConsultaSaida = new ApiCepConsultaSaida();
        OpenCepConsultaSaida openCepConsultaSaida = new OpenCepConsultaSaida();
        AwesomeApiConsultaSaida awesomeApiConsultaSaida = new AwesomeApiConsultaSaida();
        int status = 0;


        if(cepEntrada.getCep().isBlank()) {
            return ResponseEntity.badRequest().body("{\"erro\"" + ":" + "\"CEP não encontrado\"}");
        } else if (cepEntrada.getCep().length() != 8) {
            return ResponseEntity.badRequest().body("{\"erro\"" + ":" + "\"CEP inválido\"}");
        } else if(cepEntrada.getCep().matches("[A-Z][a-z]*")) {
            return ResponseEntity.badRequest().body("{\"erro\"" + ":" + "\"CEP é composto por letras\"}");
        }

        try {
            ResponseEntity<Object> responseEntity = utils.getHttp("https://cep.awesomeapi.com" +
                    ".br/json/" + cepEntrada.getCep());
            status = responseEntity.getStatusCodeValue();
            ObjectMapper mapper = new ObjectMapper();
            awesomeApiConsultaSaida = mapper.convertValue(responseEntity.getBody(), new TypeReference<AwesomeApiConsultaSaida>() {
            });

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
        } else if (viaCepConsultaSaida.getCep() != null && viaCepConsultaSaida.getErro() == null) {
            log.info("ViaCep utilizado - CEP: " + viaCepConsultaSaida.getCep() + " - HTTP STATUS - " + status);
            return ResponseEntity.status(200).body(viaCepUtils.toSingleObject(viaCepConsultaSaida));
        } else if (openCepConsultaSaida.getCep() != null && openCepConsultaSaida.getCep() == null) {
            log.info("OpenCep utilizado - CEP: " + openCepConsultaSaida.getCep() + " - HTTP STATUS - " + status);
            return ResponseEntity.status(200).body(openCepUtils.toSingleObject(openCepConsultaSaida));
        } else if (apiCepConsultaSaida.getCode() != null && apiCepConsultaSaida.getStatus() == 200) {
            log.info("ApiCep utilizado - CEP: " + apiCepConsultaSaida.getCode() + " - HTTP STATUS - " + status);
            apiCepConsultaSaida.setOrigin("ApiCep");
            return ResponseEntity.status(200).body(apiCepUtils.toSingleObject(apiCepConsultaSaida));
        }
        return ResponseEntity.status(400).body("{\"erro\"" + ":" + "\"CEP não encontrado\"}");
    }
}