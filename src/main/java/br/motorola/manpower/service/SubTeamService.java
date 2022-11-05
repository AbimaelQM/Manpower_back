package br.motorola.manpower.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.motorola.manpower.model.SubTeam;
import br.motorola.manpower.model.Team;
import br.motorola.manpower.repository.SubTeamRepository;

@Service
public class SubTeamService implements CRUDService<SubTeam>{

    private final SubTeamRepository subteamRepository;
    private final TeamService teamService;
    public SubTeamService(SubTeamRepository subteamRepository,TeamService teamService){

        this.subteamRepository = subteamRepository;
        this.teamService = teamService;
    }

    @Override
    public SubTeam save(SubTeam object) {
        
        return this.subteamRepository.save(object);
    }

    @Override
    public SubTeam getById(Long id) {
        
        return subteamRepository.findById(id).orElse(null);
    }

    @Override
    public List<SubTeam> getAll() {

        return this.subteamRepository.findAll();
    }

    @Override
    public List<SubTeam> getByAll(String termo) {
        
        return this.subteamRepository.findByAll(termo);
    }

    @Override
    public SubTeam update(SubTeam object) {
        
        Long id = object.getId();

        if(id != null){

            return this.subteamRepository.findById(id).map(registro -> {

                registro.setName(object.getName());

                Team registroTeam = this.teamService.getById(object.getTeam().getId());

                if(registroTeam == null)    

                    return null;

                registro.setTeam(registroTeam);

                return this.subteamRepository.save(registro);

            }).orElse(null);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
    
        this.subteamRepository.deleteById(id);
    }
    
}
