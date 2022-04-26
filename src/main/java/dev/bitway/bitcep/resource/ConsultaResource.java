package dev.bitway.bitcep.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.bitway.bitcep.entity.dto.*;
import dev.bitway.bitcep.service.ConsultaService;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.net.openssl.ciphers.Protocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping("/bitway/consulta")
@CrossOrigin
@Log4j2
public class ConsultaResource {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping("/cep")
    public ResponseEntity<ConsultaSaida> consultaSaidaResponseEntity(@RequestBody ConsultaEntrada consultaEntrada) {
        log.info("Consultando CEP: {}", consultaEntrada.getCep());
        consultaService.consultaCep(consultaEntrada);
        ConsultaSaida consultaSaida = consultaService.consultaCep(consultaEntrada);
        ResponseEntity<ConsultaSaida> responseEntity = ResponseEntity.ok(consultaSaida);
        log.info("Status da consulta: {}", responseEntity.getStatusCode());
        return responseEntity;
    }
    @PostMapping("/cep/soap")
    public ResponseEntity<SearchResponseSoap> searchSaidaResponseEntitySoap(@RequestBody ConsultaEntrada consultaEntrada) throws JAXBException {
        log.info("Searching CEP by SOAP request: {}", consultaEntrada.getCep());
        SearchResponseSoap searchResponseSoap = consultaService.consultaCepSoap(consultaEntrada);
        ResponseEntity<SearchResponseSoap> responseEntity = ResponseEntity.ok(searchResponseSoap);
        log.info("Status Code: {}", responseEntity.getStatusCode());
        return responseEntity;
    }


    @PostMapping("/protocol")
    public ResponseEntity<String> searchProtocol(@RequestBody SearchProtocolRequest protocolRequest) throws JsonProcessingException {
        log.info("Searching protocol by days: {}", protocolRequest.getDays());
        String protocol = consultaService.searchProtocol(protocolRequest);
        ResponseEntity<String> responseEntity = ResponseEntity.ok(protocol);
        log.info("Status Code: {}", responseEntity.getStatusCode());
        return responseEntity;
    }
}
