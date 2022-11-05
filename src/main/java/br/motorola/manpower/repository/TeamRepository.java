package br.motorola.manpower.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.motorola.manpower.model.SubTeam;
import br.motorola.manpower.model.Team;

public interface TeamRepository extends JpaRepository<Team,Long>{

    @Query("SELECT t FROM Team t INNER JOIN Usuario u ON t.usuario = u.id WHERE "+
    " t.name LIKE %?1%")
    public List<Team> findByAll(String termo);

    @Query("SELECT s FROM SubTeam s WHERE s.team.id = :team_id")
    public List<SubTeam> searchSubteamsByTeamId(@Param("team_id") Long id);
    
}
