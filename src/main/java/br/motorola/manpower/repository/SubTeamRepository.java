package br.motorola.manpower.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.motorola.manpower.model.SubTeam;

public interface SubTeamRepository extends JpaRepository<SubTeam,Long>{
    
    @Query(" SELECT s FROM SubTeam s INNER JOIN Team t ON s.team = t.id WHERE "+
           " s.name LIKE %?1% OR "+
           " t.name LIKE %?1%"
    )
    public List<SubTeam> findByAll(String termo);
}
