package br.motorola.manpower.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.motorola.manpower.model.Usuario;
import br.motorola.manpower.service.UsuarioService;

@Service
public class UsuarioConfigServices implements UserDetailsService {

    @Autowired
    private UsuarioService service;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      
        Usuario usuario = service.getUsuarioByEmailIsActivo(username);
      
        return new PerfilUsuario(usuario);
    }
    
}
