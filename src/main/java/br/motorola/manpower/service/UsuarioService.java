package br.motorola.manpower.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.motorola.manpower.model.Team;
import br.motorola.manpower.model.Usuario;
import br.motorola.manpower.repository.UsuarioRepository;

@Service
public class UsuarioService implements CRUDService<Usuario>{

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuariosRepository){

        this.usuarioRepository = usuariosRepository;

    }

    @Override
    public Usuario save(Usuario object) {
        
        return this.usuarioRepository.save(object);
    }

    @Override
    public Usuario getById(Long id) {
        
        return this.usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public List<Usuario> getAll() {

        return this.usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> getByAll(String termo) {

        return this.usuarioRepository.findByAll(termo);

    }

    @Override
    public Usuario update(Usuario object) {
       
        Long id = object.getId();

        if( id == null)

            return null;

        return this.usuarioRepository.findById(id).map(
            registro -> {
                registro.setName(object.getName());
                registro.setEmail(object.getEmail());
                registro.setPassword(object.getPassword());
                registro.setActive(object.isActive());
                registro.setRole(object.getRole());
                Usuario usuarioUpdate = registro;
                return usuarioRepository.save(usuarioUpdate);
            }
        ).orElse(null);
    }

    @Override
    public void delete(Long id) {
        
        this.usuarioRepository.deleteById(id);
        
    }

    public Usuario getUsuarioByEmailIsActivo(String email){
        return this.usuarioRepository.findByEmailUsuarioIsActivoUsuario(email);
    }
    public List<Team> searchTeamsByUsuarioId(Long id){

        return this.usuarioRepository.searchTeamsByUsuarioId(id);
    }
    
}
