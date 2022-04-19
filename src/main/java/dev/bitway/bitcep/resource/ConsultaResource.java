package dev.bitway.bitcep.resource;

import dev.bitway.bitcep.entity.dto.ConsultaEntrada;
import dev.bitway.bitcep.entity.dto.ConsultaSaida;
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
}
