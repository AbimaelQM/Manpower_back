package br.motorola.manpower.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.motorola.manpower.model.Usuario;

public class PerfilUsuario implements UserDetails{

    private Usuario usuario;

    public PerfilUsuario(Usuario usuario){

        this.usuario = usuario;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority  auth = new  SimpleGrantedAuthority ( this.usuario.getRole().name());
        return  Arrays.asList( auth );
    }

    @Override
    public String getPassword() {

        return this.usuario.getPassword();
    }

    @Override
    public String getUsername() {

        return this.usuario.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return this.usuario.isActive();
    }
    
}
