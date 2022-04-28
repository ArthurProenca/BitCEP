package dev.bitway.bitcep.entity.dto.soap;

import lombok.Data;

import java.io.Serializable;

@Data
public class BuscaProtocoloSaida implements Serializable {
    public String sdvr01;
    public String sdnxtr;
    public String id;
}

