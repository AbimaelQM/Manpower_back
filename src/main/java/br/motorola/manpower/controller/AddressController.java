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

import br.motorola.manpower.model.Address;
import br.motorola.manpower.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController implements CRUDController<Address>{

    @Autowired
    private AddressService addressService;

    @PostMapping("/")
    public ResponseEntity<Address> insert(@RequestBody Address object) {
       
        Address registro = this.addressService.save(object);

        return new ResponseEntity<>(registro,HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<List<Address>> getAll() {
       
        List<Address> registros = this.addressService.getAll();

        return new ResponseEntity<>(registros,HttpStatus.OK);

    }

    @GetMapping("/search/{termo}")
    public ResponseEntity<List<Address>> getByAll(@PathVariable("termo") String termo) {
     
        List<Address> registros = this.addressService.getByAll(termo);

        return new ResponseEntity<>(registros,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getById(@PathVariable("id") Long id) {
      
        Address registro = this.addressService.getById(id);

        if(registro == null){

            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(registro,HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Address> update(@RequestBody Address object) {
      
        Address registro = this.addressService.update(object);

        if(registro == null)
            
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        return new ResponseEntity<Address>(registro, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
       
        this.addressService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
