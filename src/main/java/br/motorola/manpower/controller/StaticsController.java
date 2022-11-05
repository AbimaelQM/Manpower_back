package br.motorola.manpower.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.motorola.manpower.model.DataStatics;
import br.motorola.manpower.model.ItemData;
import br.motorola.manpower.model.Job;
import br.motorola.manpower.model.PropertieData;
import br.motorola.manpower.service.JobService;

@RestController
@RequestMapping("/statics")
public class StaticsController {
    
    
    private JobService jobService;
    
    public StaticsController(JobService jobService){

        this.jobService = jobService;
    }
    
    @GetMapping("/job")
    public ResponseEntity<DataStatics> staticsJob() {

        DataStatics dataStatics = new DataStatics();
        
        List<Job> registros = this.jobService.getAll();
        dataStatics.setTotal(registros.size());
        dataStatics.setProperties(new Job().getProperties());

        Field[] campos = Job.class.getDeclaredFields();

        List<PropertieData> listaProperties = new ArrayList<>();
        
        for (Field propriedade : campos) {
            
            try {

                String nomeAtributo = propriedade.getName();
                propriedade.setAccessible(true);

                PropertieData prop = new PropertieData();
                prop.setName(nomeAtributo);

                
                HashMap<String,Integer> labels = new HashMap<>();
                
                for (Job item : registros){

                    Object chave = propriedade.get(item);

                    if(labels.get(chave.toString()) == null){
                        labels.put(chave.toString(), 0);
                    }

                    labels.put(chave.toString(), labels.get(chave.toString()) + 1);
                }

                for(String key: labels.keySet()){

                    ItemData item = new ItemData();
                    item.setLabel(key);
                    item.setQuantidade(labels.get(key));
                    
                    prop.setItens(item);
                }

                listaProperties.add(prop);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        dataStatics.setData(listaProperties);

        return new ResponseEntity<>(dataStatics, HttpStatus.OK);

    }

}
