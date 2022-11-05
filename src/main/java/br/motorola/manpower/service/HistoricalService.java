package br.motorola.manpower.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.motorola.manpower.model.Historical;
import br.motorola.manpower.repository.HistoricalRepository;

@Service
public class HistoricalService implements CRUDService<Historical>{

    private final HistoricalRepository repo;

    @Autowired
    public HistoricalService (HistoricalRepository repo) {
        this.repo = repo;
    }

    @Override
    public Historical getById(Long id) {
        Historical registro = repo.findById(id).orElse(null);
        return registro;
    }

    @Override
    public List<Historical> getAll() {
        List<Historical> registros = repo.findAll();
        return registros;
    }

    @Override
    public List<Historical> getByAll(String termo) {
        // List<Historical> registros = repo.findByAll(termo);
        // return registros;
        return null;
    }

    public List<Historical> getByPerson (Long id) {
        List<Historical> registros = repo.findByPerson(id);
        return registros;
    }

    public List<Historical> getByDate(Date date) {
        List<Historical> registros = repo.findByDate(date);
        return registros;
    }

    public List<Historical> getJob (Long id) {
        List<Historical>  registros = repo.findByJob(id);
        return registros;
    }

    @Override
    public Historical update(Historical object) {
        if (object.getId() == null) { return null; }

        return this.repo.findById(object.getId()).map(
            registro -> {
                registro.setDate(object.getDate());
                Historical historicalUpdate = registro;
                return repo.save(historicalUpdate);
            }
        ).orElse(null);
    }
    
    @Override
    public Historical save(Historical object) {
        return repo.save(object);
    }
    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
