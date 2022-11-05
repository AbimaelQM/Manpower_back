package br.motorola.manpower.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.motorola.manpower.model.Job;
import br.motorola.manpower.model.Person;
import br.motorola.manpower.model.PersonSkill;
import br.motorola.manpower.repository.PersonRepository;

@Service
public class PersonService implements CRUDService<Person> {

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person save(Person object) {
        return this.personRepository.save(object);
    }

    @Override
    public Person getById(Long id) {
        
        return this.personRepository.findById(id).orElse(null);
    }

    @Override
    public List<Person> getAll() {
        
        return this.personRepository.findAll();
    }

    public Page<Person> getAll(Pageable pageable) {

        return this.personRepository.findAll(pageable);
    }

    @Override
    public List<Person> getByAll(String termo) {

        return this.personRepository.findByAll(termo);
    }

    @Override
    public Person update(Person object) {

        Long id = object.getId();

        if(id != null){

            return this.personRepository.findById(id).map(registro ->{

                registro.setName(object.getName());
                registro.setEmail(object.getEmail());
                registro.setCountry(object.getCountry());
                registro.setCity(object.getCity());
                registro.setStreet(object.getStreet());
                registro.setState(object.getState());
                registro.setDistrict(object.getDistrict());
                registro.setNumber(object.getNumber());
                registro.setActive(object.isActive());
                registro.setJob(object.getJob());

                this.personRepository.save(registro);
                return registro;

            }).orElse(null);

        }
        return null;
    }

    @Override
    public void delete(Long id) {

        
        personRepository.findById(id).map(registro -> {
            registro.setActive(false);
            registro.setJob(null);
            
            personRepository.save(registro);
            return registro;
        }).orElse(null);
    
    }

    public Job searchJobByPersonId(Long id){

        return this.personRepository.searchJobByPersonId(id);
    }

    public List<Person> searchPeopleFreeJob(){

        return this.personRepository.searchPeopleFreeJob();
    }

    public List<PersonSkill> searchSkillsByPersonId(Long id){

        return this.personRepository.searchSkillsByPersonId(id);
    }
    
}
