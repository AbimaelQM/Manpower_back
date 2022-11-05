package br.motorola.manpower.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.motorola.manpower.model.Usuario;
import br.motorola.manpower.service.UsuarioService;

@RestController
@RequestMapping("/user_info")
public class LoginController {

    private final UsuarioService servico;

    @Autowired
    public LoginController(UsuarioService servico) {
        this.servico = servico;
    }

    @GetMapping("/")
    public ResponseEntity<Usuario> getUsuario() {

        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = servico.getUsuarioByEmailIsActivo(principal.getName());
        
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
    
}