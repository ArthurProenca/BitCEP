package dev.bitway.bitcep.entity.dto.apicep;

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
public class ApiCepConsultaSaida implements Serializable {
    private int status;
    private boolean ok;
    private String code;
    private String state;
    private String city;
    private String district;
    private String address;
    private String statusText;
    @Nullable
    private String category;
    @Nullable
    private String origin;
    @Nullable
    private String message;
}
