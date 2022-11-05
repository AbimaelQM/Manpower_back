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
import br.motorola.manpower.model.Skill;
import br.motorola.manpower.service.AreaService;

@RestController
@RequestMapping("/area")
public class AreaController implements CRUDController<Area>{

    private AreaService areaService;

    public AreaController(AreaService areaService){

        this.areaService = areaService;
    }

    // Cria uma área
    @PostMapping("/")
    public ResponseEntity<Area> insert(@RequestBody Area object) {
        
        Area newArea = areaService.save(object);
        return new ResponseEntity<Area>(newArea, HttpStatus.CREATED);
    
    }

    // Retorna todas as áreas
    @GetMapping("/")
    public ResponseEntity<List<Area>> getAll() {
       
        List<Area> allArea = areaService.getAll();

        return new ResponseEntity<>(allArea,HttpStatus.OK);

    }

    // Retorna uma área a partir do nome informado
    @GetMapping("/search/{termo}")
    public ResponseEntity<List<Area>> getByAll(@PathVariable(name = "termo") String termo) {

        List<Area> areas = areaService.getByAll(termo);

        return new ResponseEntity<>(areas,HttpStatus.OK);
    }   

    // Retorna uma área passando um id
    @GetMapping("/{id}")
    public ResponseEntity<Area> getById(@PathVariable(name="id") Long id) {
        
        Area registro = areaService.getById(id);
        
        if(registro == null)

            return new ResponseEntity<Area>(registro, HttpStatus.NOT_FOUND);
            
        return new ResponseEntity<>(registro,HttpStatus.OK);
    }

    // Atualiza uma área
    @PutMapping("/")
    public ResponseEntity<Area> update(@RequestBody Area object) {
       
        Area registro = areaService.update(object);
        
        if(registro == null)

            return new ResponseEntity<>(registro,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(registro,HttpStatus.OK);
    }

    // Apaga uma área
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        
        areaService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Retorna todas as skills de uma área
    @GetMapping("/searchSkillInIdArea/{id}")
    public ResponseEntity<List<Skill>> searchSkillsByAreaId(@PathVariable("id") Long id){

        List<Skill> registros = this.areaService.searchSkillsByAreaId(id);

        return new ResponseEntity<>(registros, HttpStatus.OK);
    }
    
}
