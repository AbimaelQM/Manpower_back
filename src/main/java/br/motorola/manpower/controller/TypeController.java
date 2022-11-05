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

import br.motorola.manpower.model.Type;
import br.motorola.manpower.model.Job;
import br.motorola.manpower.service.TypeService;

@RestController
@RequestMapping("/type")
public class TypeController implements CRUDController<Type>{

    private TypeService typeService;

    public TypeController(TypeService typeService){

        this.typeService = typeService;
    }

    // Cria uma área
    @PostMapping("/")
    public ResponseEntity<Type> insert(@RequestBody Type object) {
        
        Type newType = typeService.save(object);
        return new ResponseEntity<Type>(newType, HttpStatus.CREATED);
    
    }

    // Retorna todas as áreas
    @GetMapping("/")
    public ResponseEntity<List<Type>> getAll() {
       
        List<Type> allType = typeService.getAll();

        return new ResponseEntity<>(allType,HttpStatus.OK);

    }

    // Retorna uma área a partir do nome informado
    @GetMapping("/search/{termo}")
    public ResponseEntity<List<Type>> getByAll(@PathVariable(name = "termo") String termo) {

        List<Type> areas = typeService.getByAll(termo);

        return new ResponseEntity<>(areas,HttpStatus.OK);
    }   

    // Retorna uma área passando um id
    @GetMapping("/{id}")
    public ResponseEntity<Type> getById(@PathVariable(name="id") Long id) {
        
        Type registro = typeService.getById(id);
        
        if(registro == null)

            return new ResponseEntity<Type>(registro, HttpStatus.NOT_FOUND);
            
        return new ResponseEntity<>(registro,HttpStatus.OK);
    }

    // Atualiza uma área
    @PutMapping("/")
    public ResponseEntity<Type> update(@RequestBody Type object) {
       
        Type registro = typeService.update(object);
        
        if(registro == null)

            return new ResponseEntity<>(registro,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(registro,HttpStatus.OK);
    }

    // Apaga uma área
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        
        typeService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Retorna todas as skills de uma área
    @GetMapping("/searchJobInIdType/{id}")
    public ResponseEntity<List<Job>> searchJobsByTypeId(@PathVariable("id") Long id){

        List<Job> registros = this.typeService.searchJobsByTypeId(id);

        return new ResponseEntity<>(registros, HttpStatus.OK);
    }
    
}
