package br.motorola.manpower.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.motorola.manpower.model.PersonSkill;
import br.motorola.manpower.repository.PersonSkillRepository;

@Service
public class PersonSkillService implements CRUDService<PersonSkill> {

    @Autowired
    private PersonSkillRepository personSkillRepository;

    @Override
    public PersonSkill save(PersonSkill object) {
        
        return this.personSkillRepository.save(object);
    }

    @Override
    public PersonSkill getById(Long id) {
        
        return this.personSkillRepository.findById(id).orElse(null);
    }

    @Override
    public List<PersonSkill> getAll() {
        
        return this.personSkillRepository.findAll();
    }

    @Override
    public List<PersonSkill> getByAll(String termo) {

        return this.personSkillRepository.findByAll(termo);
    }

    @Override
    public PersonSkill update(PersonSkill object) {

        Long id = object.getId();

        if(id != null){

            return this.personSkillRepository.findById(id).map(registro ->{

                registro.setPerson(object.getPerson());
                registro.setSkill(object.getSkill());
                registro.setSkillLevel(object.getSkillLevel());
                this.personSkillRepository.save(registro);
                return registro;

            }).orElse(null);

        }
        return null;
    }

    @Override
    public void delete(Long id) {
          
        this.personSkillRepository.deleteById(id);
        
    }
    
}
