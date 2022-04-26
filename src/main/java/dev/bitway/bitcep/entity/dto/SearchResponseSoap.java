package dev.bitway.bitcep.entity.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SearchResponseSoap implements Serializable {
    private String cep;
    private String cidade;
    private String complemento;
    private String bairro;
    private String end;
}
