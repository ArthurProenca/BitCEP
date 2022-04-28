package dev.bitway.bitcep.entity.dto.awesomeapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AwesomeApiConsultaSaida implements Serializable {
    private String cep;
    private String address_type;
    private String address_name;
    private String address;
    private String state;
    private String district;
    private String lat;
    private String lng;
    private String city;
    private String city_ibge;
    private String ddd;
    @Nullable
    private String category;
    @Nullable
    private String origin;
}
