package dev.bitway.bitcep.service.callbacks;

import dev.bitway.bitcep.entity.dto.CepEntrada;
import dev.bitway.bitcep.entity.dto.apicep.ApiCepConsultaSaida;
import dev.bitway.bitcep.entity.dto.opencep.OpenCepConsultaSaida;
import dev.bitway.bitcep.entity.dto.viacep.ViaCepConsultaSaida;
import dev.bitway.bitcep.utils.ApiCepUtils;
import dev.bitway.bitcep.utils.ViaCepUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepConsultaService {
    private ViaCepConsultaSaida viaCepConsultaSaida = new ViaCepConsultaSaida();
    private ApiCepConsultaSaida apiCepConsultaSaida = new ApiCepConsultaSaida();
    private OpenCepConsultaSaida openCepConsultaSaida = new OpenCepConsultaSaida();
    private ViaCepUtils viaCepUtils = new ViaCepUtils();
    private ApiCepUtils apiCepUtils = new ApiCepUtils();

    private RestTemplate restTemplate = new RestTemplate();

    public ApiCepConsultaSaida apiCepConsultaSaida(CepEntrada cepEntrada) {
        try{
            ResponseEntity<ApiCepConsultaSaida> response = restTemplate.getForEntity("https://ws.apicep.com/cep/" +
                    cepEntrada.getCep() + ".json", ApiCepConsultaSaida.class);

            apiCepConsultaSaida = response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        apiCepConsultaSaida.setCategory(viaCepUtils.spliterator(apiCepConsultaSaida.getCode()));

        return apiCepConsultaSaida;
    }

    public ViaCepConsultaSaida viaCepConsultaSaida(CepEntrada cepEntrada) {
        try {
            ResponseEntity<ViaCepConsultaSaida> response = restTemplate.getForEntity("https://v2iacep.com.br/ws/" +
                    cepEntrada.getCep() + "/json/", ViaCepConsultaSaida.class);

            viaCepConsultaSaida = response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(viaCepConsultaSaida.getCep() == null) {
            return viaCepConsultaSaida;
        }
        viaCepConsultaSaida.setCategoria(viaCepUtils.spliterator(viaCepConsultaSaida.getCep()));

        return viaCepConsultaSaida;
    }

    public OpenCepConsultaSaida openCepConsultaSaida(CepEntrada cepEntrada) {
        try{
            ResponseEntity<OpenCepConsultaSaida> response = restTemplate.getForEntity("https://ope3ncep.com/v1/" +
                    cepEntrada.getCep() + ".json", OpenCepConsultaSaida.class);

            openCepConsultaSaida = response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //openCepConsultaSaida.setCategoria(viaCepUtils.spliterator(openCepConsultaSaida.getCep()));

        return openCepConsultaSaida;
    }


}
