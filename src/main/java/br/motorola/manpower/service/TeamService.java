package br.motorola.manpower.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.motorola.manpower.model.SubTeam;
import br.motorola.manpower.model.Team;
import br.motorola.manpower.model.Usuario;
import br.motorola.manpower.repository.TeamRepository;

@Service
public class TeamService implements CRUDService<Team>{

    private final TeamRepository teamRepository;
    private final UsuarioService usuarioService;
    public TeamService(TeamRepository teamRepository,UsuarioService usuarioService){

        this.teamRepository = teamRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public Team save(Team object) {
        return this.teamRepository.save(object);
    }

    @Override
    public Team getById(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    @Override
    public List<Team> getAll() {
        return this.teamRepository.findAll();

    }

    @Override
    public List<Team> getByAll(String termo) {
        return this.teamRepository.findByAll(termo);
    }

    @Override
    public Team update(Team object) {
        Long id = object.getId();

        // se foi passado um objeto Expertise com um id
        if(id != null){

            // pega ele da base de dados
            return this.teamRepository.findById(id).map(registro -> {

                // Mapea nesse objeto os atributos novos

                // Nome novo
                registro.setName(object.getName());
                registro.setJob_quantity(object.getJob_quantity());

                // Busca na base de dados o objeto area passado
                Usuario registroUsuario = this.usuarioService.getById(object.getUsuario().getId());

                // Caso o objeto area não exista
                if(registroUsuario == null)    

                    return null;

                // Caso exista atualize
                registro.setUsuario(registroUsuario);

                
                return this.teamRepository.save(registro);

            }).orElse(null);
        }

        // No caso de não terem passado o id
        return null;
    }

    @Override
    public void delete(Long id) {
        
        this.teamRepository.deleteById(id);
    }

    public List<SubTeam> searchSubteamsByTeamId(Long id){

        return this.teamRepository.searchSubteamsByTeamId(id);
    }
    
}
