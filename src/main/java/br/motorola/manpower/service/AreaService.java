package br.motorola.manpower.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.motorola.manpower.model.Area;
import br.motorola.manpower.model.Skill;
import br.motorola.manpower.repository.AreaRepository;

@Service
public class AreaService implements CRUDService<Area>{

    private AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository){

        this.areaRepository = areaRepository;
    }

    @Override
    public Area save(Area object) {
        
        return this.areaRepository.save(object);
    }

    @Override
    public Area getById(Long id) {
        
        return this.areaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Area> getAll() {
        
        return this.areaRepository.findAll();
    }


    @Override
    public List<Area> getByAll(String termo) {
        
        List<Area> registros = areaRepository.buscaTermo(termo);
        return registros;
    }

    @Override
    public Area update(Area object) {
        
        Long id = object.getId();

        if(id == null)

            return null;

        return this.areaRepository.findById(id).map(
            registro -> {
                registro.setName(object.getName());
                Area areaUpdate = registro;
                return areaRepository.save(areaUpdate);
            }
        ).orElse(null);

    }

    @Override
    public void delete(Long id) {

        this.areaRepository.deleteById(id);
        
    }
   

    public List<Skill> searchSkillsByAreaId(Long id){

        return this.areaRepository.searchSkillsByAreaId(id);
    }
    
}
