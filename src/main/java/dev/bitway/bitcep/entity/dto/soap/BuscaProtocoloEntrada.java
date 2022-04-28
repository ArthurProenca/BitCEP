package dev.bitway.bitcep.entity.dto.soap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class BuscaProtocoloEntrada implements Serializable {
    private String days;
}
