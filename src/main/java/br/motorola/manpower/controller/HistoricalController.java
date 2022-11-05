package br.motorola.manpower.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.motorola.manpower.model.Historical;
import br.motorola.manpower.service.HistoricalService;

@RestController
@RequestMapping("/historical")
public class HistoricalController implements CRUDController<Historical> {

    private final HistoricalService servico;

    @Autowired
    public HistoricalController (HistoricalService servico) {
        this.servico = servico;
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Historical> insert(@RequestBody Historical object) {

        Historical check = servico.getById(object.getId());
        if (check != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Historical newHistorical = servico.save(object);
        return new ResponseEntity<>(newHistorical, HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<Historical>> getAll() {
        List<Historical> allHistorics = servico.getAll();
        return new ResponseEntity<>(allHistorics, HttpStatus.OK);
    }

    @Override
    @GetMapping("/search/{termoBusca}")
    public ResponseEntity<List<Historical>> getByAll(@PathVariable("termoBusca") String termo) {
        List<Historical> registros = servico.getByAll(termo);
        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Historical> getById(@PathVariable("id") Long id) {
        Historical registro = servico.getById(id);
        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<Historical> update(@RequestBody Historical object) {
        Historical registroUpdate = servico.update(object);
        return new ResponseEntity<>(registroUpdate, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        servico.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
