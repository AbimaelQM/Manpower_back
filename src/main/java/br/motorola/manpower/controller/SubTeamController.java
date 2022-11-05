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

import br.motorola.manpower.model.SubTeam;
import br.motorola.manpower.model.Team;
import br.motorola.manpower.service.SubTeamService;
import br.motorola.manpower.service.TeamService;

@RestController
@RequestMapping("/subteam")
public class SubTeamController implements CRUDController<SubTeam>{

    private final SubTeamService subteamService;
    private final TeamService teamService;

    public SubTeamController(SubTeamService subteamService,TeamService teamService){

        this.subteamService = subteamService;
        this.teamService = teamService;
    }

    @PostMapping("/")
    public ResponseEntity<SubTeam> insert(@RequestBody SubTeam object) {
        
        // Get the team from subteam to valid if exists
        Team team = this.teamService.getById(object.getTeam().getId());
        
        if(team != null){//valid
            object.setTeam(team);
        }else{//invalid
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        SubTeam registro = subteamService.save(object);  
        return new ResponseEntity<>(registro,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubTeam> getById(@PathVariable(name="id") Long id) {

        SubTeam registro = this.subteamService.getById(id);

        if(registro == null)
            return new ResponseEntity<>(registro,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(registro,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<SubTeam>> getAll() {

        List<SubTeam> registros = subteamService.getAll();

        return new ResponseEntity<>(registros,HttpStatus.OK);
    }

    @GetMapping("/search/{termo}")
    public ResponseEntity<List<SubTeam>> getByAll(@PathVariable(name = "termo") String termo) {

        List<SubTeam> registros = this.subteamService.getByAll(termo);

        return new ResponseEntity<>(registros,HttpStatus.OK);

    }

    @PutMapping("/")
    public ResponseEntity<SubTeam> update(@RequestBody SubTeam object) {

        SubTeam registro = subteamService.update(object);

        if(registro == null)

            return new ResponseEntity<>(registro,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(registro,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        this.subteamService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
