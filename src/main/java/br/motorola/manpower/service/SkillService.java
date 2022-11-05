package br.motorola.manpower.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.motorola.manpower.model.Area;
import br.motorola.manpower.model.Job;
import br.motorola.manpower.model.Skill;
import br.motorola.manpower.repository.SkillRepository;

@Service
public class SkillService implements CRUDService<Skill>{

    private final SkillRepository skillRepository;
    private final AreaService areaService;
    public SkillService(SkillRepository skillRepository,AreaService areaService){

        this.skillRepository = skillRepository;
        this.areaService = areaService;
    }

    @Override
    public Skill save(Skill object) {
        
        return this.skillRepository.save(object);
    }

    @Override
    public Skill getById(Long id) {
        
        return skillRepository.findById(id).orElse(null);
    }

    @Override
    public List<Skill> getAll() {

        return this.skillRepository.findAll();
    }

    @Override
    public List<Skill> getByAll(String termo) {
        
        return this.skillRepository.findByAll(termo);
    }

    @Override
    public Skill update(Skill object) {
        
        Long id = object.getId();

        // se foi passado um objeto Expertise com um id
        if(id != null){

            // pega ele da base de dados
            return this.skillRepository.findById(id).map(registro -> {

                // Mapea nesse objeto os atributos novos

                // Nome novo
                registro.setName(object.getName());

                // Busca na base de dados o objeto area passado
                Area registroArea = this.areaService.getById(object.getArea().getId());

                // Caso o objeto area não exista
                if(registroArea == null)    

                    return null;

                // Caso exista atualize
                registro.setArea(registroArea);

                
                return this.skillRepository.save(registro);

            }).orElse(null);
        }

        // No caso de não terem passado o id
        return null;
    }

    @Override
    public void delete(Long id) {

        this.skillRepository.deleteById(id);

    }

    public List<Job> searchJobsBySkillId(Long id){

        return this.skillRepository.searchJobsBySkillId(id);
    }
    
}
