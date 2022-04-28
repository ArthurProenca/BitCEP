package dev.bitway.bitcep.utils;

import dev.bitway.bitcep.entity.dto.viacep.ViaCepConsultaSaida;

public class ViaCepUtils {
    public String spliterator(String sufix) {
        int aux = sufix.indexOf('-');
        String temp = sufix.substring(aux + 1, sufix.length());
        int length = Integer.parseInt(temp);
        System.out.println(length);
        if (length >= 001 && length <= 899) {
            return "Logradouro";
        } else if (length >= 900 && length <= 959) {
            return "Código especial";
        } else if (length >= 960 && length <= 969) {
            return "Promocional";
        } else if (length >= 970 && length <= 989 || length == 999) {
            return "Unidade dos Correios";
        } else if (length >= 990 && length <= 998) {
            return "Caixa postal comunitária";
        } else {
            return "Municipal";
        }
    }

    public ViaCepConsultaSaida trataRetorno(ViaCepConsultaSaida viaCepConsultaSaida) {
        if (viaCepConsultaSaida.getLogradouro() == "") {
            viaCepConsultaSaida.setLogradouro("N/A");
        }
        if (viaCepConsultaSaida.getBairro() == "") {
            viaCepConsultaSaida.setBairro("N/A");
        }
        if (viaCepConsultaSaida.getComplemento() == "") {
            viaCepConsultaSaida.setComplemento("N/A");
        }
        if (viaCepConsultaSaida.getGia() == "") {
            viaCepConsultaSaida.setGia("N/A");
        }
        return viaCepConsultaSaida;
    }
}
