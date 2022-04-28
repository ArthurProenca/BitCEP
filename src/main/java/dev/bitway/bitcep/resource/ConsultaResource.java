package dev.bitway.bitcep.resource;

import dev.bitway.bitcep.entity.dto.CepEntrada;
import dev.bitway.bitcep.service.AwesomeApiConsultaService;
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
    private AwesomeApiConsultaService awesomeApiConsultaService;

    @PostMapping("/cep")
    public ResponseEntity<?> consultaSaidaResponseEntity(@RequestBody CepEntrada cepEntrada) {
        log.info("Consultando CEP: {}", cepEntrada.getCep());
        return awesomeApiConsultaService.consultaCep(cepEntrada);

    }
}
