package dev.bitway.bitcep.service;

import dev.bitway.bitcep.entity.dto.ConsultaEntrada;
import dev.bitway.bitcep.entity.dto.ConsultaSaida;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsultaService {

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
            return "Ppromocional";
        } else if (length >= 970 && length <= 989 || length == 999) {
            return "Unidade dos Correios";
        } else if (length >= 990 && length <= 998) {
            return "Caixa postal comunitária";
        } else {
            return "Municipal";
        }
    }

    public ConsultaSaida trataRetorno(ConsultaSaida consultaSaida) {
        if(consultaSaida.getLogradouro() == "") {
            consultaSaida.setLogradouro("N/A");
        }
        if(consultaSaida.getBairro() == "") {
            consultaSaida.setBairro("N/A");
        }
        if(consultaSaida.getComplemento() == "") {
            consultaSaida.setComplemento("N/A");
        }
        if(consultaSaida.getGia() == "") {
            consultaSaida.setGia("N/A");
        }
        return consultaSaida;
    }

    public ConsultaSaida consultaCep(ConsultaEntrada consultaEntrada) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ConsultaSaida> response = restTemplate.getForEntity("https://viacep.com.br/ws/" +
                consultaEntrada.getCep() + "/json/", ConsultaSaida.class);
        ConsultaSaida consultaSaida = response.getBody();

        consultaSaida.setCategoria(spliterator(consultaSaida.getCep()));

        return trataRetorno(consultaSaida);
    }
}
