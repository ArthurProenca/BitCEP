package dev.bitway.bitcep.utils;

import dev.bitway.bitcep.entity.dto.CepSaida;
import dev.bitway.bitcep.entity.dto.opencep.OpenCepConsultaSaida;

public class OpenCepUtils {
    private CepSaida cepSaida = new CepSaida();
    private Utils utils = new Utils();

    public CepSaida toSingleObject(OpenCepConsultaSaida openCepConsultaSaida) {
        cepSaida.setCep(openCepConsultaSaida.getCep());
        cepSaida.setLogradouro(openCepConsultaSaida.getLogradouro() == "" ? "N/A" : openCepConsultaSaida.getLogradouro());
        cepSaida.setBairro(openCepConsultaSaida.getBairro());
        cepSaida.setCidade(openCepConsultaSaida.getLocalidade());
        cepSaida.setUf(openCepConsultaSaida.getUf());
        cepSaida.setComplemento(openCepConsultaSaida.getComplemento() == "" ? "N/A" : openCepConsultaSaida.getComplemento());
        cepSaida.setIbge(openCepConsultaSaida.getIbge() == "" ? "N/A" : openCepConsultaSaida.getIbge());
        cepSaida.setCategoria(utils.spliterator(openCepConsultaSaida.getCep()));
        cepSaida.setGia("N/A");
        cepSaida.setDdd("N/A");
        cepSaida.setLongitude("N/A");
        cepSaida.setLatitude("N/A");
        cepSaida.setSiafi("N/A");
        cepSaida.setOrigem("OpenCep");

        return cepSaida;
    }
}
