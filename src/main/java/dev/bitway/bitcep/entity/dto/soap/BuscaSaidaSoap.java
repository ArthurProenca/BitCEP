package dev.bitway.bitcep.entity.dto.soap;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class BuscaSaidaSoap implements Serializable {
    private String cep;
    private String cidade;
    private String complemento2;
    private String bairro;
    private String end;
}
