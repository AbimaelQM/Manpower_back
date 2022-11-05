package br.motorola.manpower.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.motorola.manpower.model.Team;
import br.motorola.manpower.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

    public List<Usuario> findUsuariosByEmail(String email);
    

    @Query(" SELECT u FROM Usuario u WHERE email LIKE %:termo% OR name LIKE %:termo% OR role LIKE %:termo%")
    public List<Usuario> findByAll(@Param("termo") String termo);

    @Query(" SELECT u FROM Usuario u WHERE email LIKE :email")
    public Usuario findByEmailUsuarioIsActivoUsuario(@Param("email") String email);

    public boolean findByEmail(String email);

    @Query("SELECT t FROM Team t WHERE t.usuario.id = :usuario_id")
    public List<Team> searchTeamsByUsuarioId(@Param("usuario_id") Long id);

}
