package dev.bitway.bitcep.utils;

import dev.bitway.bitcep.entity.dto.CepSaida;
import dev.bitway.bitcep.entity.dto.awesomeapi.AwesomeApiConsultaSaida;

public class AwesomeApiCepUtils {

    private CepSaida cepSaida = new CepSaida();

    private Utils utils = new Utils();

    public CepSaida toSingleObject(AwesomeApiConsultaSaida awesomeApiConsultaSaida) {
        cepSaida.setCep(awesomeApiConsultaSaida.getCep());
        cepSaida.setComplemento(awesomeApiConsultaSaida.getAddress_type());
        cepSaida.setCategoria(utils.spliterator(awesomeApiConsultaSaida.getCep()));
        cepSaida.setLogradouro(awesomeApiConsultaSaida.getAddress_name());
        cepSaida.setUf(awesomeApiConsultaSaida.getState());
        cepSaida.setBairro(awesomeApiConsultaSaida.getDistrict());
        cepSaida.setLatitude(awesomeApiConsultaSaida.getLat());
        cepSaida.setLongitude(awesomeApiConsultaSaida.getLng());
        cepSaida.setCidade(awesomeApiConsultaSaida.getCity());
        cepSaida.setIbge(awesomeApiConsultaSaida.getCity_ibge());
        cepSaida.setDdd(awesomeApiConsultaSaida.getDdd());
        cepSaida.setGia("N/A");
        cepSaida.setSiafi("N/A");
        cepSaida.setCategoria(utils.spliterator(awesomeApiConsultaSaida.getCep()));
        cepSaida.setOrigem("AwesomeApi");
        return cepSaida;
    }
}
