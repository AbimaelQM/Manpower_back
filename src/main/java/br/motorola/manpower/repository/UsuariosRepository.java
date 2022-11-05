package br.motorola.manpower.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.motorola.manpower.model.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario,Long>{

    public List<Usuario> findUsuariosByEmail(String email);
    

    @Query(" SELECT u FROM Usuario u WHERE email LIKE %:termo%")
    public List<Usuario> findByAll(@Param("termo") String termo);

    @Query(" SELECT u FROM Usuario u WHERE email = :email")
    public Usuario findByEmailUsuarioIsActivoUsuario(@Param("email") String email);

}
