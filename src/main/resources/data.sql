

-- id, nome
INSERT INTO area (id,name) VALUES 
(1,'Artificial Intelligence'),
(2,'Data Analysis'),
(3,'SW Configuration Management'),
(4,'UI/UX Customer Experience '),
(5,'SW Development'),
(6,'Testing'),
(7,'Project Management');

INSERT INTO skill (id,name,area_id) VALUES 
(1,'Cloud Big Data Engineer',1),
(2,'Data Engineer',1),
(3,'Data Scientist',1),
(4,'Lead Data Scientist',1),
(5,'Machine Learning Engineer',1),
(6,'Mobile Machine Learning Engineer',1),
(7,'Post Launch Data Analyst',2),
(8,'Pre Launch Data Analyst',2),
(9,'CM Engineer',3),
(10,'Dev Operations',3),
(11,'Platform Integration',3),
(12,'UI/UX SME',4),
(13,'Android Apps',5),
(14,'Android Core Apps',5),
(15,'Android Framework Generic',5), 
(16,'Android Framework Telephony',5), 
(17,'Android Framework UI',5), 
(18,'Android Security',5), 
(19,'Android System SW',5), 
(20,'Apps Technical Lead',5), 
(21,'Camera Engine',5), 
(22,'Data Engineer',5), 
(23,'Front End',5), 
(24,'Full Stack',5), 
(25,'Internacionalization',5), 
(26,'IOS App Developer',5), 
(27,'Localization Integration',5), 
(28,'Product Integration Technical Lead',5), 
(29,'Scrum Master',5), 
(30,'SW Engineering Management',5), 
(31,'Technical Lead',5), 
(32,'Android Enterprise',5),
(33,'SW Test',6),
(34,'Technical Lead',6),
(35,'User Experience Test',6),
(36,'DogFood Lead',7),
(37,'SW Integration',7),
(38,'Program Manager',7),
(39,'Global Test Lead',7),
(40,'User Trial Lead',7),
(41,'Project Lead',7);

INSERT INTO usuario (id,active,email,name,role,password) VALUES 
/* 
    usuario: admin@gmail.com
    senha:admin
*/
(1,1,'admin@gmail.com','Admin','ROLE_ADMIN','$2a$10$zArhMpAXoIhDRvqmJSPI2uDJbGxoQft9g2LCa.7fBCOXDuqKVC6cS'),
(2,1,'vic@gmail.com','Victoria','ROLE_SLT','$2a$10$zArhMpAXoIhDRvqmJSPI2uDJbGxoQft9g2LCa.7fBCOXDuqKVC6cS'),
(3,1,'abimael.queirozl@gmail.com','Abimael','ROLE_SLT','$2a$10$zArhMpAXoIhDRvqmJSPI2uDJbGxoQft9g2LCa.7fBCOXDuqKVC6cS'),
(4,1,'leandro@gmail.com','Leandro','ROLE_USER','$2a$10$zArhMpAXoIhDRvqmJSPI2uDJbGxoQft9g2LCa.7fBCOXDuqKVC6cS');

INSERT INTO team (id,name, job_quantity, usuario_id) VALUES 
(1,'DF', 5, 2),
(2,'GPD', 8, 3),
(3,'Upgrades Core Product', 10, 2),
(4,'Mototalk', 6, 3);

INSERT INTO sub_team (id,name, team_id) VALUES 
(1,'Analytics', 1),
(2,'Apps', 1),
(3,'DF-Data', 1),
(4,'Logistics', 1),
(5,'Analytics', 2),
(6,'Deployment', 2),
(7,'NPI Tech Lead', 2),
(8,'Validation', 3),
(9,'PM', 3),
(10,'SSW', 3),
(11,'ASW', 3),
(12,'Development', 4);
                                                
INSERT INTO skill_level (id,name) VALUES 
(1,'Junior'),
(2,'Pleno'),
(3,'Senior');

INSERT INTO address (id,country, city, state) VALUES 
  (1,"Brazil","Campinas","São Paulo"),
  (2,"Brazil","Manaus","Amazonas"),
  (3,"Brazil","Porto Alegre","Rio Grande do Sul"),
  (4,"Brazil","Brasília","Distrito Federal");

INSERT INTO person (id,name, email, country, state, city, district, street, number,active) VALUES 
  (1,"Gerlâne","ger@gmail.edu","France","Paris","Saint-Denis","Mountmorency","Jean Jaurès",56,1),
  (2,"Murilo","murilo@gmail.com","Brazil","Acre","Rio Branco","Sobral","Estrada da Sobral",154,1),
  (3,"Christopher","cris@outlook.edu",null,null,null,null,null,null,1),
  (4,"Guilherme","gui@outlook.edu",null,null,null,null,null,null,1),
  (5,"Victor","r9@outlook.edu",null,null,null,null,null,null,1),
  (6,"Paulo","checkin@outlook.edu",null,null,null,null,null,null,1),
  (7,"Daniel","omelhor@outlook.edu",null,null,null,null,null,null,1),
  (8,"Laura","orientadora@outlook.edu",null,null,null,null,null,null,1),
  (9,"Daricélio","coord@outlook.edu",null,null,null,null,null,null,1),
  (10,"Nasserala","redes@outlook.edu",null,null,null,null,null,null,1);

INSERT INTO type (id,name) VALUES 
(1,'FTE'),
(2,'Intern'),
(3,'Contractor');

INSERT INTO modality (id,name) VALUES 
(1,'Remote'),
(2,'Presential'),
(3,'Hybrid');

INSERT INTO job (id,type_id, modality_id, skill_level_id, skill_id, address_id, sub_team, person_id, active, area_id, team_id) VALUES 
(1,1,2,3,4,1,1,1,1,1,1),
(2,2,1,2,1,2,2,2,1,1,1),
(3,2,1,2,6,2,2,3,1,1,1),
(4,2,1,2,23,4,2,4,1,5,1),
(5,2,1,2,16,4,6,5,1,5,2),
(6,2,1,2,32,3,5,6,1,5,2),
(7,2,1,2,32,3,8,null,1,5,3),
(8,2,1,2,32,3,9,null,1,5,3),
(9,2,1,2,32,3,10,null,1,5,3),
(10,2,1,2,32,3,11,null,1,5,3),
(11,2,1,2,32,3,11,null,1,5,3);

INSERT INTO person_skill (id,person_id, skill_id, skill_level_id, area_id) VALUES 
  (1,1,1,1,1),
  (2,2,2,3,1),
  (3,3,3,2,1),
  (4,4,23,1,1),
  (5,5,24,3,1),
  (6,6,5,3,1),
  (7,7,6,3,2),
  (8,8,23,2,1),
  (9,9,14,2,2),
  (10,10,11,1,1);

-- DROP TRIGGER IF EXISTS job_AFTER_INSERT; 

CREATE TRIGGER job_AFTER_INSERT
AFTER INSERT ON `job` FOR EACH ROW 
BEGIN
	INSERT INTO historical(date,job_id,person_id) VALUES(date(current_date()), NEW.id, null);
END


CREATE TRIGGER job_AFTER_UPDATE AFTER UPDATE ON job FOR EACH ROW BEGIN
	IF (NEW.person_id != OLD.person_id) THEN
		INSERT INTO `manpower`.`historical` (`date`,`job_id`,`person_id`) VALUES (date(current_date()), OLD.id, OLD.person_id);
        INSERT INTO `historical` (`date`,`job_id`,`person_id`) VALUES (date(current_date()), OLD.id, NEW.person_id);
    ELSE
		INSERT INTO `manpower`.`historical` (`date`, `job_id`,`person_id`) VALUES (date(current_date()), OLD.id, NEW.person_id);
	END IF;
END;