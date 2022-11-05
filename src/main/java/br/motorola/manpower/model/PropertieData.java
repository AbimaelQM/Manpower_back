package br.motorola.manpower.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertieData {
    
    private String name;
    private List<ItemData> itens;

    public PropertieData(){

        this.itens = new ArrayList<>();
    }
    public void setItens(ItemData item){

        
        this.itens.add(item);
    }
}
