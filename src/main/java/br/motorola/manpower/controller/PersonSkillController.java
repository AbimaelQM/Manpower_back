package br.motorola.manpower.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.motorola.manpower.model.PersonSkill;
import br.motorola.manpower.service.PersonSkillService;

@RestController
@RequestMapping("/personSkill")
public class PersonSkillController implements CRUDController<PersonSkill>{

    @Autowired
    private PersonSkillService personSkillService;


    @PostMapping("/")
    public ResponseEntity<PersonSkill> insert(@RequestBody PersonSkill object) {
        
        PersonSkill registro = this.personSkillService.save(object);

        return new ResponseEntity<>(registro,HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<PersonSkill>> getAll() {
       
        List<PersonSkill> registros = this.personSkillService.getAll();

        return new ResponseEntity<>(registros,HttpStatus.OK);
    }

    @GetMapping("/search/{termo}")
    public ResponseEntity<List<PersonSkill>> getByAll(@PathVariable("termo") String termo) {
        
        List<PersonSkill> registros = this.personSkillService.getByAll(termo);

        return new ResponseEntity<>(registros,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonSkill> getById(@PathVariable("id") Long id) {
        
        PersonSkill registro = this.personSkillService.getById(id);

        if(registro == null){

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(registro,HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<PersonSkill> update(@RequestBody PersonSkill object) {
        
        PersonSkill registro = this.personSkillService.update(object);

        if(registro ==null)

            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(registro,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        
        this.personSkillService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
