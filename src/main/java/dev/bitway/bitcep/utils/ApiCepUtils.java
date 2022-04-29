package dev.bitway.bitcep.utils;

import dev.bitway.bitcep.entity.dto.CepEntrada;
import dev.bitway.bitcep.entity.dto.CepSaida;
import dev.bitway.bitcep.entity.dto.apicep.ApiCepConsultaSaida;
import dev.bitway.bitcep.entity.dto.viacep.ViaCepConsultaSaida;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ApiCepUtils {
    private CepSaida cepSaida = new CepSaida();
    private Utils utils = new Utils();

    public ApiCepConsultaSaida trataRetorno(ApiCepConsultaSaida apiCepConsultaSaida) {
        if (apiCepConsultaSaida.getAddress() == "") {
            apiCepConsultaSaida.setAddress("N/A");
        }
        if (apiCepConsultaSaida.getDistrict() == "") {
            apiCepConsultaSaida.setDistrict("N/A");
        }
        if (apiCepConsultaSaida.getState() == "") {
            apiCepConsultaSaida.setState("N/A");
        }
        return apiCepConsultaSaida;
    }

    public CepSaida toSingleObject(ApiCepConsultaSaida apiCepConsultaSaida) {
        cepSaida.setCep(apiCepConsultaSaida.getCode());
        cepSaida.setUf(apiCepConsultaSaida.getState());
        cepSaida.setCidade(apiCepConsultaSaida.getCity() == "" ? "N/A" : apiCepConsultaSaida.getCity());
        cepSaida.setBairro(apiCepConsultaSaida.getDistrict() == "" ? "N/A" : apiCepConsultaSaida.getDistrict());
        cepSaida.setLogradouro(apiCepConsultaSaida.getAddress() == "" ? "N/A" : apiCepConsultaSaida.getAddress());
        cepSaida.setIbge("N/A");
        cepSaida.setGia("N/A");
        cepSaida.setCategoria(apiCepConsultaSaida.getCode());
        cepSaida.setComplemento("N/A");
        cepSaida.setSiafi("N/A");
        cepSaida.setDdd("N/A");
        cepSaida.setLongitude("N/A");
        cepSaida.setLatitude("N/A");
        cepSaida.setOrigem("ApiCep");
        return cepSaida;
    }
}
