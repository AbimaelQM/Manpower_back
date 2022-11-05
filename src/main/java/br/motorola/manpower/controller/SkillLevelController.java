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

import br.motorola.manpower.model.SkillLevel;
import br.motorola.manpower.model.Job;
import br.motorola.manpower.service.SkillLevelService;

@RestController
@RequestMapping("/skill_level")
public class SkillLevelController implements CRUDController<SkillLevel> {

    private SkillLevelService skillLevelService;

    public SkillLevelController( SkillLevelService skillLevelService){
        this.skillLevelService = skillLevelService;
    }

    @PostMapping("/")
    public ResponseEntity<SkillLevel> insert(@RequestBody SkillLevel object) {
        SkillLevel skillLevel = skillLevelService.save(object);
        return new ResponseEntity<SkillLevel>(skillLevel, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<SkillLevel>> getAll() {
        List<SkillLevel> skillLevels = skillLevelService.getAll();
        return new ResponseEntity<>(skillLevels,HttpStatus.OK);
    }

    @GetMapping("/search/{termo}")
    public ResponseEntity<List<SkillLevel>> getByAll(@PathVariable(name = "termo") String termo) {

        List<SkillLevel> skillLevels = skillLevelService.getByAll(termo);
        return new ResponseEntity<>(skillLevels,HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SkillLevel> getById(@PathVariable(name = "id") Long id) {
        SkillLevel skillLevel = skillLevelService.getById(id);
        
        if(skillLevel == null)
            return new ResponseEntity<>(skillLevel, HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(skillLevel, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<SkillLevel> update(@RequestBody SkillLevel object) {

        SkillLevel skillLevel = skillLevelService.update(object);

        if(skillLevel==null)
            return new ResponseEntity<>(skillLevel, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(skillLevel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {

        skillLevelService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/searchJobInIdSkillLevel/{id}")
    public ResponseEntity<List<Job>> searchJobsBySkillLevelId(@PathVariable("id") Long id){

        List<Job> registros = this.skillLevelService.searchJobsBySkillLevelId(id);

        return new ResponseEntity<>(registros, HttpStatus.OK);
    }
    
}
