package br.motorola.manpower.service;

import java.util.List;

import br.motorola.manpower.model.Job;
import org.springframework.stereotype.Service;

import br.motorola.manpower.model.SkillLevel;
import br.motorola.manpower.repository.SkillLevelRepository;

@Service
public class SkillLevelService implements CRUDService<SkillLevel>{

    private SkillLevelRepository skillLevelRepository;
    
    public SkillLevelService(SkillLevelRepository skillLevelRepository){
        this.skillLevelRepository = skillLevelRepository;
    }

    @Override
    public SkillLevel save(SkillLevel object) {

        return this.skillLevelRepository.save(object);
    }

    @Override
    public SkillLevel getById(Long id) {

        return this.skillLevelRepository.findById(id).orElse(null);
    }

    @Override
    public List<SkillLevel> getAll() {

        return this.skillLevelRepository.findAll();
    }

    @Override
    public List<SkillLevel> getByAll(String searchTerm) {
        List<SkillLevel> registros = skillLevelRepository.buscaTermo(searchTerm);
        return registros;
    }

    @Override
    public SkillLevel update(SkillLevel object) {
        Long id = object.getId();

        if(id == null)
            return null;

        return this.skillLevelRepository.findById(id).map(
            registro -> {
                registro.setName(object.getName());
                SkillLevel skillLevelUpdate = registro;
                return skillLevelRepository.save(skillLevelUpdate);
            }
        ).orElse(null);
    }

    @Override
    public void delete(Long id) {
        this.skillLevelRepository.deleteById(id);        
    }

    public List<Job> searchJobsBySkillLevelId(Long id){

        return this.skillLevelRepository.searchJobsBySkillLevelId(id);
    }
    
}
