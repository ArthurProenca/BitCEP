package dev.bitway.bitcep.resource;

import dev.bitway.bitcep.entity.dto.ConsultaEntrada;
import dev.bitway.bitcep.entity.dto.ConsultaSaida;
import dev.bitway.bitcep.entity.dto.SearchProtocolRequest;
import dev.bitway.bitcep.entity.dto.SearchResponseSoap;
import dev.bitway.bitcep.service.ConsultaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<SearchResponseSoap> searchSaidaResponseEntitySoap(@RequestBody ConsultaEntrada consultaEntrada) {
        log.info("Searching CEP by SOAP request: {}", consultaEntrada.getCep());
        SearchResponseSoap searchResponseSoap = consultaService.consultaCepSoap(consultaEntrada);
        ResponseEntity<SearchResponseSoap> responseEntity = ResponseEntity.ok(searchResponseSoap);
        log.info("Status Code: {}", responseEntity.getStatusCode());
        return responseEntity;
    }


    @PostMapping("/protocol")
    public ResponseEntity<String> searchProtocol(@RequestBody SearchProtocolRequest protocolRequest) {
        log.info("Searching protocol by days: {}", protocolRequest.getDays());
        String protocol = consultaService.searchProtocol(protocolRequest);
        ResponseEntity<String> responseEntity = ResponseEntity.ok(protocol);
        log.info("Status Code: {}", responseEntity.getStatusCode());
        return responseEntity;
    }
}
