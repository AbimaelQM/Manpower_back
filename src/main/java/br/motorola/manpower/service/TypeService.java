package br.motorola.manpower.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.motorola.manpower.model.Type;
import br.motorola.manpower.model.Job;
import br.motorola.manpower.repository.TypeRepository;

@Service
public class TypeService implements CRUDService<Type>{

    private TypeRepository typeRepository;

    public TypeService(TypeRepository areaRepository){

        this.typeRepository = areaRepository;
    }

    @Override
    public Type save(Type object) {
        
        return this.typeRepository.save(object);
    }

    @Override
    public Type getById(Long id) {
        
        return this.typeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Type> getAll() {
        
        return this.typeRepository.findAll();
    }


    @Override
    public List<Type> getByAll(String termo) {
        
        List<Type> registros = typeRepository.buscaTermo(termo);
        return registros;
    }

    @Override
    public Type update(Type object) {
        
        Long id = object.getId();

        if(id == null)
            return null;

        return this.typeRepository.findById(id).map(
            registro -> {
                registro.setName(object.getName());
                Type typeUpdate = registro;
                return typeRepository.save(typeUpdate);
            }
        ).orElse(null);

    }

    @Override
    public void delete(Long id) {

        this.typeRepository.deleteById(id);
        
    }
   

    public List<Job> searchJobsByTypeId(Long id){

        return this.typeRepository.searchJobsByTypeId(id);
    }
    
}
