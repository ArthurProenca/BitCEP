package dev.bitway.bitcep.entity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
//public class SearchProtocolResponse implements Serializable {
//    private static final long serialVersionUID = 1L;
//    private List<ProtocolResponse> protocolResponseList;
//    private Long total;
//}
public class SearchProtocolResponse implements Serializable {
    public String sdvr01;
    public String sdnxtr;
    public String id;
}

