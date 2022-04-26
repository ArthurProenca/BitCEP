package dev.bitway.bitcep.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SearchProtocolResponse implements Serializable {
    public String sdvr01;
    public String sdnxtr;
    public String id;
}

