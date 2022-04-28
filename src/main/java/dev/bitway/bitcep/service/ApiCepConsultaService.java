package dev.bitway.bitcep.service;

import dev.bitway.bitcep.entity.dto.CepEntrada;
import dev.bitway.bitcep.entity.dto.apicep.ApiCepConsultaSaida;
import dev.bitway.bitcep.entity.dto.viacep.ViaCepConsultaSaida;
import dev.bitway.bitcep.utils.ApiCepUtils;
import dev.bitway.bitcep.utils.ViaCepUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiCepConsultaService {
    private ViaCepUtils viaCepUtils = new ViaCepUtils();
    private ApiCepUtils apiCepUtils = new ApiCepUtils();

    public ApiCepConsultaSaida consultaCep(CepEntrada cepEntrada) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ApiCepConsultaSaida> response = restTemplate.getForEntity("https://ws.apicep.com/cep/" +
                cepEntrada.getCep() + ".json", ApiCepConsultaSaida.class);

        if(response.getStatusCodeValue() != 200) {
            //Trying to get the data from another webservice
        }
        ApiCepConsultaSaida apiCepConsultaSaida = response.getBody();
        apiCepConsultaSaida.setCategory(viaCepUtils.spliterator(apiCepConsultaSaida.getCode()));

        return apiCepConsultaSaida;
    }
}
