package br.motorola.manpower.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.motorola.manpower.model.Address;

@Repository
public interface AddressRepositoty extends JpaRepository<Address,Long>{
    
    @Query("SELECT a FROM Address a WHERE a.country LIKE %:termo% OR"+ 
                                        " a.city LIKE %:termo%")
    public List<Address> findByAll(@Param("termo") String termo);
}
