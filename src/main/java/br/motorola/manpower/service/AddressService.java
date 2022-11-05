package br.motorola.manpower.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.motorola.manpower.model.Address;
import br.motorola.manpower.repository.AddressRepositoty;

@Service
public class AddressService implements CRUDService<Address> {

    @Autowired
    private AddressRepositoty addressRepository;

    @Override
    public Address save(Address object) {

        return this.addressRepository.save(object);
    }

    @Override
    public Address getById(Long id) {

        return this.addressRepository.findById(id).orElse(null);
    }

    @Override
    public List<Address> getAll() {

        return this.addressRepository.findAll();
    }

    @Override
    public List<Address> getByAll(String termo) {

        return this.addressRepository.findByAll(termo);
    }

    @Override
    public Address update(Address object) {

        Long id = object.getId();

        if (id != null) {

            return this.addressRepository.findById(id).map(registro -> {

                registro.setCity(object.getCity());
                registro.setCountry(object.getCountry());
                registro.setState(object.getState());
                this.addressRepository.save(registro);
                return registro;
            }).orElse(null);

        }

        return null;
    }

    @Override
    public void delete(Long id) {
       
        this.addressRepository.deleteById(id);

    }

}
