package dev.bitway.bitcep.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CepSaida {

    private String cep;
    private String complemento;
    private String logradouro;
    private String bairro;
    private String uf;
    private String ibge;
    private String gia;
    private String categoria;
    private String latitude;
    private String longitude;
    private String cidade;
    private String ddd;
    private String siafi;
    private String origem;
}
