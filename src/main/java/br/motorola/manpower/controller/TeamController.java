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
import br.motorola.manpower.model.Usuario;
import br.motorola.manpower.service.TeamService;
import br.motorola.manpower.service.UsuarioService;

@RestController
@RequestMapping("/team")
public class TeamController implements CRUDController<Team>{

    private final TeamService teamService;
    private final UsuarioService usuarioService;

    public TeamController(TeamService teamService, UsuarioService usuarioService){
        this.teamService = teamService;
        this.usuarioService = usuarioService;
    }
    @PostMapping("/")
    public ResponseEntity<Team> insert(@RequestBody Team object){
        Usuario usuario = this.usuarioService.getById(object.getUsuario().getId());
        if(usuario != null){
            object.setUsuario(usuario);
        }else{
            // Caso não exista é retornado status de recurso não encontrado
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        Team registro = teamService.save(object);
        return new ResponseEntity<>(registro, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Team>> getAll() {
        List<Team> registros = teamService.getAll();

        return new ResponseEntity<>(registros,HttpStatus.OK);
    }

    @GetMapping("/search/{termo}")
    public ResponseEntity<List<Team>> getByAll(@PathVariable(name = "termo") String termo) {
        List<Team> registros = this.teamService.getByAll(termo);

        return new ResponseEntity<>(registros,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getById(@PathVariable(name = "id") Long id) {
        Team registro = this.teamService.getById(id);

        if(registro == null)
            return new ResponseEntity<>(registro,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(registro,HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Team> update(@RequestBody Team object){
        Team registro = teamService.update(object);

        if(registro == null)

            return new ResponseEntity<>(registro,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(registro,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        this.teamService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/searchSubteamInIdTeam/{id}")
    public ResponseEntity<List<SubTeam>> searchSubteamsByTeamId(@PathVariable("id") Long id){

        List<SubTeam> registros = this.teamService.searchSubteamsByTeamId(id);

        return new ResponseEntity<>(registros, HttpStatus.OK);
    }
    
}
