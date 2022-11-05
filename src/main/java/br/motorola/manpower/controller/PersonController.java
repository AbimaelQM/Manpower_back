package br.motorola.manpower.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import br.motorola.manpower.model.Job;
import br.motorola.manpower.model.Person;
import br.motorola.manpower.model.PersonSkill;
import br.motorola.manpower.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController implements CRUDController<Person>{

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @Autowired
    private PersonService personService;

    @PostMapping("/")
    public ResponseEntity<Person> insert(@RequestBody Person object) {

        Person registro = this.personService.save(object);
        
        return new ResponseEntity<>(registro,HttpStatus.CREATED);
    }

    // @GetMapping("/")
    public ResponseEntity<List<Person>> getAll() {
       
        List<Person> registros = this.personService.getAll();

        return new ResponseEntity<>(registros,HttpStatus.OK);
    }

    // @GetMapping("/")
    public ResponseEntity<Page<Person>> getAll(Pageable pageable) {

        Page<Person> registros = personService.getAll(pageable);

        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Person>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {

        Pageable paging = PageRequest.of(page, size);
        
        Page<Person> registros = personService.getAll(paging);

        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @GetMapping("/search/{termo}")
    public ResponseEntity<List<Person>> getByAll(@PathVariable("termo") String termo) {
        
        List<Person> registros = this.personService.getByAll(termo);

        return new ResponseEntity<>(registros,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable("id") Long id) {
        
        Person registro = this.personService.getById(id);

        if(registro == null){

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(registro,HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Person> update(@RequestBody Person object) {
        
        Person registro = this.personService.update(object);

        if(registro ==null)

            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(registro,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        if(personService.getById(id).getJob() != null){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        } else {
            this.personService.delete(id);
        }
        

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/searchPeopleFreeJob")
    public ResponseEntity<List<Person>> searchPeopleFreeJob(){

        List<Person> registro = this.personService.searchPeopleFreeJob();

        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    @GetMapping("/searchJobInIdPerson/{id}")
    public ResponseEntity<Job> searchJobByPersonId(@PathVariable("id") Long id){

        Job registro = this.personService.searchJobByPersonId(id);

        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    @GetMapping("/searchSkillInIdPerson/{id}")
    public ResponseEntity<List<PersonSkill>> searchSkillsByPersonId(@PathVariable("id") Long id){

        List<PersonSkill> registros = this.personService.searchSkillsByPersonId(id);

        return new ResponseEntity<>(registros, HttpStatus.OK);
    }
    
}
