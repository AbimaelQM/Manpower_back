package br.motorola.manpower.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.motorola.manpower.model.Modality;
import br.motorola.manpower.model.Job;
import br.motorola.manpower.service.ModalityService;

@RestController
@RequestMapping("/modality")
public class ModalityController implements CRUDController<Modality>{

    private ModalityService modalityService;

    public ModalityController(ModalityService modalityService){

        this.modalityService = modalityService;
    }

    // Cria uma área
    @PostMapping("/")
    public ResponseEntity<Modality> insert(@RequestBody Modality object) {
        
        Modality newModality = modalityService.save(object);
        return new ResponseEntity<Modality>(newModality, HttpStatus.CREATED);
    
    }

    // Retorna todas as áreas
    @GetMapping("/")
    public ResponseEntity<List<Modality>> getAll() {
       
        List<Modality> allModality = modalityService.getAll();

        return new ResponseEntity<>(allModality,HttpStatus.OK);

    }

    // Retorna uma área a partir do nome informado
    @GetMapping("/search/{termo}")
    public ResponseEntity<List<Modality>> getByAll(@PathVariable(name = "termo") String termo) {

        List<Modality> modalitys = modalityService.getByAll(termo);

        return new ResponseEntity<>(modalitys,HttpStatus.OK);
    }   

    // Retorna uma área passando um id
    @GetMapping("/{id}")
    public ResponseEntity<Modality> getById(@PathVariable(name="id") Long id) {
        
        Modality registro = modalityService.getById(id);
        
        if(registro == null)

            return new ResponseEntity<Modality>(registro, HttpStatus.NOT_FOUND);
            
        return new ResponseEntity<>(registro,HttpStatus.OK);
    }

    // Atualiza uma área
    @PutMapping("/")
    public ResponseEntity<Modality> update(@RequestBody Modality object) {
       
        Modality registro = modalityService.update(object);
        
        if(registro == null)

            return new ResponseEntity<>(registro,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(registro,HttpStatus.OK);
    }

    // Apaga uma área
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        
        modalityService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Retorna todas as skills de uma área
    @GetMapping("/searchJobInIdModality/{id}")
    public ResponseEntity<List<Job>> searchJobsByModalityId(@PathVariable("id") Long id){

        List<Job> registros = this.modalityService.searchJobsByModalityId(id);

        return new ResponseEntity<>(registros, HttpStatus.OK);
    }
    
}
