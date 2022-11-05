package br.motorola.manpower.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataStatics {
    
    private Integer total;
    private List<PropertieData> data;
    private List<String> properties;
}
