package dev.bitway.bitcep.resource;

import dev.bitway.bitcep.entity.dto.CepEntrada;
import dev.bitway.bitcep.entity.dto.soap.BuscaProtocoloEntrada;
import dev.bitway.bitcep.entity.dto.soap.BuscaSaidaSoap;
import dev.bitway.bitcep.service.SoapService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bitway/soap")
@CrossOrigin
@Log4j2
public class SoapResource {

    @Autowired
    private SoapService soapService;

    @PostMapping("/cep/soap")
    public ResponseEntity<BuscaSaidaSoap> searchSaidaResponseEntitySoap(@RequestBody CepEntrada cepEntrada) {
        log.info("Searching CEP by SOAP request: {}", cepEntrada.getCep());
        BuscaSaidaSoap buscaSaidaSoap = soapService.consultaCepSoap(cepEntrada);
        ResponseEntity<BuscaSaidaSoap> responseEntity = ResponseEntity.ok(buscaSaidaSoap);
        log.info("Status Code: {}", responseEntity.getStatusCode());
        return responseEntity;
    }


    @PostMapping("/protocol")
    public ResponseEntity<String> searchProtocol(@RequestBody BuscaProtocoloEntrada protocolRequest) {
        log.info("Searching protocol by days: {}", protocolRequest.getDays());
        String protocol = soapService.searchProtocol(protocolRequest);
        ResponseEntity<String> responseEntity = ResponseEntity.ok(protocol);
        log.info("Status Code: {}", responseEntity.getStatusCode());
        return responseEntity;
    }
}
