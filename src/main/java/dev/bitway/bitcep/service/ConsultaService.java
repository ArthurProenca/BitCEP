package dev.bitway.bitcep.service;

import dev.bitway.bitcep.entity.dto.ConsultaEntrada;
import dev.bitway.bitcep.entity.dto.ConsultaSaida;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsultaService {

    public ResponseEntity<ConsultaSaida> consultaCep(ConsultaEntrada consultaEntrada) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ConsultaSaida> response = restTemplate.getForEntity("https://viacep.com.br/ws/" +
                consultaEntrada.getCep() + "/json/", ConsultaSaida.class);
        return response;
    }
}
