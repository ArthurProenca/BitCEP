package dev.bitway.bitcep.utils;

import dev.bitway.bitcep.entity.dto.apicep.ApiCepConsultaSaida;
import dev.bitway.bitcep.entity.dto.viacep.ViaCepConsultaSaida;

public class ApiCepUtils {
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
}
