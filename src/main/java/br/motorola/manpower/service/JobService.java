package br.motorola.manpower.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.motorola.manpower.model.SkillLevel;
import br.motorola.manpower.model.SubTeam;
import br.motorola.manpower.model.Team;
import br.motorola.manpower.model.Type;
import br.motorola.manpower.model.Address;
import br.motorola.manpower.model.Area;
import br.motorola.manpower.model.Job;
import br.motorola.manpower.model.Modality;
import br.motorola.manpower.model.Person;
import br.motorola.manpower.model.Skill;
import br.motorola.manpower.repository.JobRepository;

@Service
public class JobService implements CRUDService<Job> {

    private final JobRepository jobRepository;
    private final SkillLevelService skillLevelService;
    private final SkillService skillService;
    private final AreaService areaService;
    private final TypeService typeService;
    private final ModalityService modalityService;
    private final AddressService addressService;
    private final SubTeamService subTeamService;
    private final PersonService personService;
    private final TeamService teamService;

    public JobService(
            JobRepository jobRepository,
            SkillLevelService skillLevelService,
            TypeService typeService,
            ModalityService modalityService,
            SkillService skillService,
            AddressService addressService,
            SubTeamService subTeamService,
            PersonService personService,
            AreaService areaService,
            TeamService teamService) {

        this.jobRepository = jobRepository;
        this.skillLevelService = skillLevelService;
        this.skillService = skillService;
        this.areaService = areaService;
        this.typeService = typeService;
        this.modalityService = modalityService;
        this.addressService = addressService;
        this.subTeamService = subTeamService;
        this.personService = personService;
        this.teamService = teamService;
    }

    @Override
    public Job save(Job object) {

        return this.jobRepository.save(object);
    }

    @Override
    public Job getById(Long id) {

        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public List<Job> getAll() {

        return this.jobRepository.findAll();
    }

    public Page<Job> getAll(Pageable pageable) {

        return this.jobRepository.findByActive(pageable);
    }

    @Override
    public List<Job> getByAll(String termo) {

        return this.jobRepository.findByAll(termo);
    }

  

    @Override
    public Job update(Job object) {

        Long id = object.getId();

        if (id != null) {

            return this.jobRepository.findById(id).map(registro -> {

                registro.setType(object.getType());
                registro.setModality(object.getModality());
                registro.setSkillLevel(object.getSkillLevel());
                registro.setSkill(object.getSkill());
                registro.setArea(object.getArea());
                registro.setAddress(object.getAddress());
                registro.setSubTeam(object.getSubTeam());
                registro.setPerson(object.getPerson());
                registro.setDescription(object.getDescription());
                registro.setActive(object.isActive());
                registro.setTeam(object.getTeam());

                SkillLevel registroSkillLevel = this.skillLevelService.getById(object.getSkillLevel().getId());
                Skill registroSkill = this.skillService.getById(object.getSkill().getId());
                Area registroArea = this.areaService.getById(object.getArea().getId());
                Type registroType = this.typeService.getById(object.getType().getId());
                Modality registromModality = this.modalityService.getById(object.getModality().getId());
                Address registroAddress = this.addressService.getById(object.getAddress().getId());
                SubTeam registroSubTeam = this.subTeamService.getById(object.getSubTeam().getId());
                if (object.getPerson() != null) {
                    Person registroPerson = this.personService.getById(object.getPerson().getId());
                    registro.setPerson(registroPerson);
                }
                Team registroTeam = this.teamService.getById(object.getTeam().getId());

                if (registroSkillLevel == null)
                    return null;

                if (registroSkill == null)
                    return null;

                if (registroArea == null)
                    return null;

                if (registroAddress == null)
                    return null;

                if (registroSubTeam == null)
                    return null;

                if (registroTeam == null)
                    return null;

                registro.setSkillLevel(registroSkillLevel);
                registro.setSkill(registroSkill);
                registro.setArea(registroArea);
                registro.setType(registroType);
                registro.setModality(registromModality);
                registro.setAddress(registroAddress);
                registro.setSubTeam(registroSubTeam);
                registro.setTeam(registroTeam);

                return this.jobRepository.save(registro);

            }).orElse(null);
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        jobRepository.findById(id).map(registro -> {
            registro.setActive(false);

            if (registro.getPerson() != null) {
                personService.getById(registro.getPerson().getId()).setJob(null);
                registro.setPerson(null);
            }

            jobRepository.save(registro);
            return registro;
        }).orElse(null);
    }

}
