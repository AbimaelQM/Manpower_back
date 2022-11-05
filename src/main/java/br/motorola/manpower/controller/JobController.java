package br.motorola.manpower.controller;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.motorola.manpower.model.Address;
import br.motorola.manpower.model.Area;
import br.motorola.manpower.model.Job;
import br.motorola.manpower.model.Modality;
import br.motorola.manpower.model.Skill;
import br.motorola.manpower.model.SkillLevel;
import br.motorola.manpower.model.SubTeam;
import br.motorola.manpower.model.Team;
import br.motorola.manpower.model.Type;
import br.motorola.manpower.service.AddressService;
import br.motorola.manpower.service.AreaService;
import br.motorola.manpower.service.JobService;
import br.motorola.manpower.service.ModalityService;
import br.motorola.manpower.service.SkillLevelService;
import br.motorola.manpower.service.SkillService;
import br.motorola.manpower.service.SubTeamService;
import br.motorola.manpower.service.TeamService;
import br.motorola.manpower.service.TypeService;

@RestController
@RequestMapping("/job")
public class JobController implements CRUDController<Job> {

    private final TypeService typeService;
    private final ModalityService modalityService;
    private final JobService jobService;
    private final SkillLevelService skillLevelService;
    private final SkillService skillService;
    private final AreaService areaService;
    private final AddressService addressService;
    private final SubTeamService subTeamService;
    private final TeamService teamService;

    public JobController(
            JobService jobService,
            SkillLevelService skillLevelService,
            SkillService skillService,
            AddressService addressService,
            SubTeamService subTeamService,
            TypeService typeService,
            ModalityService modalityService,
            AreaService areaService,
            TeamService teamService) {
        this.modalityService = modalityService;
        this.typeService = typeService;
        this.jobService = jobService;
        this.skillLevelService = skillLevelService;
        this.skillService = skillService;
        this.areaService = areaService;
        this.addressService = addressService;
        this.subTeamService = subTeamService;
        this.teamService = teamService;
    }

    @PostMapping("/")
    public ResponseEntity<Job> insert(@RequestBody Job object) {

        SkillLevel skillLevel = this.skillLevelService.getById(object.getSkillLevel().getId());
        Type type = this.typeService.getById(object.getType().getId());
        Modality modality = this.modalityService.getById(object.getModality().getId());
        Skill skill = this.skillService.getById(object.getSkill().getId());
        Area area = this.areaService.getById(object.getArea().getId());
        Address address = this.addressService.getById(object.getAddress().getId());
        SubTeam subTeam = this.subTeamService.getById(object.getSubTeam().getId());
        Team team = this.teamService.getById(object.getTeam().getId());

        if (type != null) {
            object.setType(type);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if (modality != null) {
            object.setModality(modality);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if (skillLevel != null) {
            object.setSkillLevel(skillLevel);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if (skill != null) {
            object.setSkill(skill);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if (address != null) {
            object.setAddress(address);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if (subTeam != null) {
            object.setSubTeam(subTeam);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if (area != null) {
            object.setArea(area);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if (team != null) {
            int count = 0;
            for (SubTeam subTeamCount : team.getSubTeams()) {
                count = count + subTeamCount.getJobs().size();
            }
            if (count >= team.getJob_quantity()) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            } else {
                object.setTeam(team);
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Job registro = jobService.save(object);
        return new ResponseEntity<>(registro, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Job>> getAll() {

        List<Job> registros = jobService.getAll();

        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Job>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {

        Pageable paging = PageRequest.of(page, size);

        Page<Job> registros = jobService.getAll(paging);

        return new ResponseEntity<>(registros, HttpStatus.OK);
    }

    @GetMapping("/search/{termo}")
    public ResponseEntity<List<Job>> getByAll(@PathVariable(name = "termo") String termo) {

        List<Job> registros = this.jobService.getByAll(termo);

        return new ResponseEntity<>(registros, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getById(@PathVariable(name = "id") Long id) {

        Job registro = this.jobService.getById(id);

        if (registro == null)
            return new ResponseEntity<>(registro, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

  

    @PutMapping("/")
    public ResponseEntity<Job> update(@RequestBody Job object) {

       
        Job registro = jobService.update(object);

        if (registro == null)

            return new ResponseEntity<>(registro, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(registro, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        this.jobService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
