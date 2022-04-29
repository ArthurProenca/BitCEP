package dev.bitway.bitcep.utils;

import dev.bitway.bitcep.entity.dto.CepSaida;
import dev.bitway.bitcep.entity.dto.viacep.ViaCepConsultaSaida;

public class ViaCepUtils {
    private CepSaida cepSaida = new CepSaida();
    private Utils utils = new Utils();

    public CepSaida toSingleObject(ViaCepConsultaSaida viaCepConsultaSaida) {
        cepSaida.setCep(viaCepConsultaSaida.getCep());
        cepSaida.setLogradouro(viaCepConsultaSaida.getLogradouro() == "" ? "N/A" : viaCepConsultaSaida.getLogradouro());
        cepSaida.setUf(viaCepConsultaSaida.getUf());
        cepSaida.setBairro(viaCepConsultaSaida.getBairro() == "" ? "N/A" : viaCepConsultaSaida.getBairro());
        cepSaida.setLatitude("N/A");
        cepSaida.setLongitude("N/A");
        cepSaida.setComplemento(viaCepConsultaSaida.getComplemento() == "" ? "N/A" : viaCepConsultaSaida.getComplemento());
        cepSaida.setCidade(viaCepConsultaSaida.getLocalidade());
        cepSaida.setIbge(viaCepConsultaSaida.getIbge());
        cepSaida.setDdd(viaCepConsultaSaida.getDdd());
        cepSaida.setGia(viaCepConsultaSaida.getGia() == "" ? "N/A" : viaCepConsultaSaida.getGia());
        cepSaida.setCategoria(utils.spliterator(viaCepConsultaSaida.getCep()));
        cepSaida.setSiafi(viaCepConsultaSaida.getSiafi());
        cepSaida.setOrigem("ViaCep");
        return cepSaida;
    }
}
