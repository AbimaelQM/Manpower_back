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

import br.motorola.manpower.model.Area;
import br.motorola.manpower.model.Job;
import br.motorola.manpower.model.Skill;
import br.motorola.manpower.service.AreaService;
import br.motorola.manpower.service.SkillService;

@RestController
@RequestMapping("/skill")
public class SkillController implements CRUDController<Skill>{

    private final SkillService skillService;
    private final AreaService areaService;

    public SkillController(SkillService skillService,AreaService areaService){

        this.skillService = skillService;
        this.areaService = areaService;
    }

    @PostMapping("/")
    public ResponseEntity<Skill> insert(@RequestBody Skill object) {
        

        // Obtem a area da skill para valida se existe
        Area area = this.areaService.getById(object.getArea().getId());
        // se a area existir ela é mapeada no atribut area da skill
        if(area != null){
            object.setArea(area);
        }else{
            // Caso não exista é retornado status de recurso não encontrado
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        Skill registro = skillService.save(object);  
        return new ResponseEntity<>(registro,HttpStatus.CREATED);
    }


    @GetMapping("/")
    public ResponseEntity<List<Skill>> getAll() {

        List<Skill> registros = skillService.getAll();

        return new ResponseEntity<>(registros,HttpStatus.OK);
    }

    @GetMapping("/search/{termo}")
    public ResponseEntity<List<Skill>> getByAll(@PathVariable(name = "termo") String termo) {

        List<Skill> registros = this.skillService.getByAll(termo);

        return new ResponseEntity<>(registros,HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> getById(@PathVariable(name="id") Long id) {

        Skill registro = this.skillService.getById(id);

        if(registro == null)
            return new ResponseEntity<>(registro,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(registro,HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Skill> update(@RequestBody Skill object) {

        Skill registro = skillService.update(object);

        if(registro == null)

            return new ResponseEntity<>(registro,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(registro,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        
        this.skillService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/searchJobInIdSkill/{id}")
    public ResponseEntity<List<Job>> searchJobsBySkillId(@PathVariable("id") Long id){

        List<Job> registros = this.skillService.searchJobsBySkillId(id);

        return new ResponseEntity<>(registros, HttpStatus.OK);
    }
    
}
