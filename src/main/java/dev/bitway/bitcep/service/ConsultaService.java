package dev.bitway.bitcep.service;

import com.google.gson.Gson;
import dev.bitway.bitcep.entity.dto.ConsultaEntrada;
import dev.bitway.bitcep.entity.dto.ConsultaSaida;
import dev.bitway.bitcep.entity.dto.SearchProtocolRequest;
import dev.bitway.bitcep.entity.dto.SearchResponseSoap;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

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
        if (consultaSaida.getLogradouro() == "") {
            consultaSaida.setLogradouro("N/A");
        }
        if (consultaSaida.getBairro() == "") {
            consultaSaida.setBairro("N/A");
        }
        if (consultaSaida.getComplemento() == "") {
            consultaSaida.setComplemento("N/A");
        }
        if (consultaSaida.getGia() == "") {
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

    public SearchResponseSoap consultaCepSoap(ConsultaEntrada consultaEntrada) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity("https://apps.correios.com.br/SigepMasterJPA/AtendeClienteService/AtendeCliente?wsdl", soapMessageCep(consultaEntrada.getCep()), String.class);
        String res = Objects.requireNonNull(response.getBody()).replace("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns2:consultaCEPResponse xmlns:ns2=\"http://cliente.bean.master.sigep.bsb.correios.com.br/\"><return>", "");
        res = res.replace("</return></ns2:consultaCEPResponse></soap:Body></soap:Envelope>", "");
        return new Gson().fromJson(String.valueOf(XML.toJSONObject(res)), SearchResponseSoap.class);//Daria mt babalho colocar os N/A, e como só eu vou usar esse service, não precisaria de um service para isso
    }

    private String soapMessageCep(String cep) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cli=\"http://cliente.bean.master.sigep.bsb.correios.com.br/\"><soapenv:Header/><soapenv:Body><cli:consultaCEP><cep>" +
                cep +
                "</cep></cli:consultaCEP></soapenv:Body></soapenv:Envelope>";
    }

    private String soapMessageProtocol(String data) {
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Header/><soapenv:Body><cli:iNumberOfDays_INT01><NumberOfDays>" +
                data + "</NumberOfDays></cli:iNumberOfDays_INT01></soapenv:Body></soapenv:Envelope>";
    }

    public String searchProtocol(SearchProtocolRequest protocolo) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8081/body",
                soapMessageProtocol(protocolo.getDays()), String.class);
        JSONObject object = XML.toJSONObject(Objects.requireNonNull(response.getBody()));
        String res = object.toString();
        int inicio = res.indexOf("[");
        int fim = res.indexOf("]");
        return res.substring(inicio, fim + 1);
    }
}