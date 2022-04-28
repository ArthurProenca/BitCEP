package dev.bitway.bitcep.service;

import com.google.gson.Gson;
import dev.bitway.bitcep.entity.dto.CepEntrada;
import dev.bitway.bitcep.entity.dto.soap.BuscaProtocoloEntrada;
import dev.bitway.bitcep.entity.dto.soap.BuscaSaidaSoap;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class SoapService {
    public BuscaSaidaSoap consultaCepSoap(CepEntrada cepEntrada) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity("https://apps.correios.com.br/SigepMasterJPA/AtendeClienteService/AtendeCliente?wsdl", soapMessageCep(cepEntrada.getCep()), String.class);
        String res = Objects.requireNonNull(response.getBody()).replace("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns2:consultaCEPResponse xmlns:ns2=\"http://cliente.bean.master.sigep.bsb.correios.com.br/\"><return>", "");
        res = res.replace("</return></ns2:consultaCEPResponse></soap:Body></soap:Envelope>", "");
        return new Gson().fromJson(String.valueOf(XML.toJSONObject(res)), BuscaSaidaSoap.class);//Daria mt babalho colocar os N/A, e como só eu vou usar esse service, não precisaria de um service para isso
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

    public String searchProtocol(BuscaProtocoloEntrada protocolo) {
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
