package dev.bitway.bitcep.entity.dto;

import lombok.*;

import java.io.Serializable;
import javax.xml.bind.annotation.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

//@XmlElement(name="return")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchResponseSoap implements Serializable {
    private String cep;
    private String cidade;
    private String complemento2;
    private String bairro;
    private String end;
}
