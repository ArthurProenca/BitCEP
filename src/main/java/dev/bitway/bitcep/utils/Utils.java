package dev.bitway.bitcep.utils;

import dev.bitway.bitcep.entity.dto.CepEntrada;
import dev.bitway.bitcep.entity.dto.apicep.ApiCepConsultaSaida;
import dev.bitway.bitcep.entity.dto.awesomeapi.AwesomeApiConsultaSaida;
import dev.bitway.bitcep.entity.dto.opencep.OpenCepConsultaSaida;
import dev.bitway.bitcep.entity.dto.viacep.ViaCepConsultaSaida;
import dev.bitway.bitcep.service.callbacks.CepConsultaService;

public class Utils {

    public String spliterator(String sufix) {
        String temp = sufix.substring(sufix.length() - 3);
        int length = Integer.parseInt(temp);
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

}
