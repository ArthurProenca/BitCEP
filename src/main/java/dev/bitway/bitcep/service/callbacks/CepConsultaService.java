package dev.bitway.bitcep.service.callbacks;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.bitway.bitcep.entity.dto.CepEntrada;
import dev.bitway.bitcep.entity.dto.apicep.ApiCepConsultaSaida;
import dev.bitway.bitcep.entity.dto.opencep.OpenCepConsultaSaida;
import dev.bitway.bitcep.entity.dto.viacep.ViaCepConsultaSaida;
import dev.bitway.bitcep.utils.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CepConsultaService {
    private Utils utils = new Utils();
    private  ResponseEntity<Object> response = null;

    public ApiCepConsultaSaida apiCepConsultaSaida(CepEntrada cepEntrada) {
        try {
            response = utils.getHttp("https://ws.apicep.com/cep/" +
                    cepEntrada.getCep() + ".json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(response.getBody(), new TypeReference<ApiCepConsultaSaida>() {});
    }

    public ViaCepConsultaSaida viaCepConsultaSaida(CepEntrada cepEntrada) {
        try {
            response = utils.getHttp("https://viacep.com.br/ws/" +
                    cepEntrada.getCep() + "/json/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(response.getBody(), new TypeReference<ViaCepConsultaSaida>() {});
    }

    public OpenCepConsultaSaida openCepConsultaSaida(CepEntrada cepEntrada) {
        try {
            response = utils.getHttp("https://opencep.com/v1/" +
                    cepEntrada.getCep() + ".json");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(response.getBody(), new TypeReference<OpenCepConsultaSaida>() {});
    }


}
