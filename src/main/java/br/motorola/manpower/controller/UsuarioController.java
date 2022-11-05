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

import br.motorola.manpower.model.Team;
import br.motorola.manpower.model.Usuario;
import br.motorola.manpower.service.UsuarioService;
@RestController
@RequestMapping("/user")
public class UsuarioController implements CRUDController<Usuario>{

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){

        this.usuarioService = usuarioService;

    }

    @PostMapping("/")
    public ResponseEntity<Usuario> insert(@RequestBody Usuario object) {

        Usuario registro = this.usuarioService.save(object);
        return new ResponseEntity<>(registro,HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> getAll() {
        

        List<Usuario> registros = this.usuarioService.getAll();

        return new ResponseEntity<>(registros,HttpStatus.OK);
    }

    @GetMapping("/search/{termo}")
    public ResponseEntity<List<Usuario>> getByAll(@PathVariable("termo") String termo) {

        List<Usuario> registros = this.usuarioService.getByAll(termo);
        return new ResponseEntity<>(registros,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable("id") Long id) {
        
        Usuario registro = this.usuarioService.getById(id);

        if( registro == null)
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(registro,HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Usuario> update(@RequestBody Usuario object) {


           
        Usuario registro = this.usuarioService.update(object);

        if(registro == null){

            return new ResponseEntity<>(registro,HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(registro,HttpStatus.OK);
        
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        
        Usuario registro = this.usuarioService.getById(id);

        if(registro == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        

        this.usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/searchTeamInIdUsuario/{id}")
    public ResponseEntity<List<Team>> searchTeamsByUsuarioId(@PathVariable("id") Long id){

        List<Team> registros = this.usuarioService.searchTeamsByUsuarioId(id);

        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @GetMapping("/getUsuarioByEmailIsActivo/{email}")
    public ResponseEntity<Usuario> getUsuarioByEmailIsActivo(@PathVariable("email") String email){
        Usuario registros = this.usuarioService.getUsuarioByEmailIsActivo(email);

        return new ResponseEntity<>(registros, HttpStatus.OK);
    }
   
}
