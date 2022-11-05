package br.motorola.manpower.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.motorola.manpower.model.Modality;
import br.motorola.manpower.model.Job;
import br.motorola.manpower.repository.ModalityRepository;

@Service
public class ModalityService implements CRUDService<Modality>{

    private ModalityRepository modalityRepository;

    public ModalityService(ModalityRepository modalityRepository){

        this.modalityRepository = modalityRepository;
    }

    @Override
    public Modality save(Modality object) {
        
        return this.modalityRepository.save(object);
    }

    @Override
    public Modality getById(Long id) {
        
        return this.modalityRepository.findById(id).orElse(null);
    }

    @Override
    public List<Modality> getAll() {
        
        return this.modalityRepository.findAll();
    }


    @Override
    public List<Modality> getByAll(String termo) {
        
        List<Modality> registros = modalityRepository.buscaTermo(termo);
        return registros;
    }

    @Override
    public Modality update(Modality object) {
        
        Long id = object.getId();

        if(id == null)

            return null;

        return this.modalityRepository.findById(id).map(
            registro -> {
                registro.setName(object.getName());
                Modality modalityUpdate = registro;
                return modalityRepository.save(modalityUpdate);
            }
        ).orElse(null);

    }

    @Override
    public void delete(Long id) {

        this.modalityRepository.deleteById(id);
        
    }
   

    public List<Job> searchJobsByModalityId(Long id){

        return this.modalityRepository.searchJobsByModalityId(id);
    }
    
}
