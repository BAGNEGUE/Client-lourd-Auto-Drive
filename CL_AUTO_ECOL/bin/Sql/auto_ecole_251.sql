drop database if exists auto_ecole_251;
create database auto_ecole_251;
use auto_ecole_251;
CREATE TABLE CANDIDAT
(
  IDcandidat int NOT NULL auto_increment ,
  PHOTO VARCHAR(100) NULL  ,
   NOM VARCHAR(32) NULL  ,
   PRENOM VARCHAR(32) NULL  ,
   AGE INT(3) NOT NULL,
   EMAIL VARCHAR(32) NULL  ,
    MDP VARCHAR(32) NULL ,
    Type_user enum("candidat"),
   NUMERO_TELEPHONE VARCHAR(32) NULL  ,
     PRIMARY KEY (IDCANDIDAT)
);
DROP TABLE IF EXISTS MONITEUR;
CREATE TABLE MONITEUR
(
   IDmoniteur int(3) NOT NULL auto_increment ,
   PHOTO VARCHAR(100) NULL  ,
   NOM VARCHAR(32) NULL  ,
   PRENOM VARCHAR(32) NULL  ,
   EMAIL VARCHAR(32) NULL  ,
   MDP VARCHAR(32) NULL ,
  Type_user enum("MONITEUR"),
   NUMERO_TELEPHONE VARCHAR(32) NULL  ,
   idresponsable int (2),
   PRIMARY KEY (IDMONITEUR)
);
CREATE TABLE EXAMEN
(
   IDEXAMEN int NOT NULL auto_increment  ,
   DATE_ET_HEURE_D_EXAMEN VARCHAR(32) NULL  ,
   VEHICULE VARCHAR(32) NULL  ,
   TYPE_DE_PERMIS VARCHAR(32) NULL ,
   PRIMARY KEY (IDEXAMEN)
);
CREATE TABLE VEHICULE
(
   IDVEHICULE int(3) NOT NULL auto_increment ,
   MARQUE VARCHAR(32) NULL  ,
   MODELE VARCHAR(32) NULL  ,
   IMMATRICULATION VARCHAR(32) NULL,
   PRIMARY KEY (IDVEHICULE)
);
CREATE TABLE LECON
(
   IDLECON int(3) NOT NULL auto_increment,
   TYPE_DE_LECON VARCHAR(32) NULL  ,
   DESCRIPTION VARCHAR(32) NULL  ,
   TITRE VARCHAR(32) NULL ,
   PRIMARY KEY (IDLECON)
);
CREATE TABLE PLANNINGS
(
   IDPLANNING int not null auto_increment,
   IDLECON int(3) NOT NULL  ,
   IDCANDIDAT int(3) NOT NULL  ,
   DATEHEURDEBUT DATETIME NOT NULL  ,
   IDMONITEUR int(3) NOT NULL  ,
   DATEHEURFIN DATETIME NULL  ,
   ETAT enum ('annule','valide','en attente') ,
   PRIMARY KEY (IDPLANNING),
   constraint fk_lecon foreign key (IDLECON) references LECON(IDLECON),
   constraint fk_candidat foreign key (IDcandidat) references CANDIDAT( IDcandidat ),
   constraint fk_moniteur foreign key (IDMONITEUR) references MONITEUR(IDMONITEUR)
);
 
create table responsable(
idresponsable int not null auto_increment,
PHOTO VARCHAR(100) NULL  ,
nom varchar(30),
prenom varchar(30),
email varchar(30),
mdp varchar(50),
Type_user enum("responsable"),
NUMERO_TELEPHONE VARCHAR(32) NULL  ,
PRIMARY KEY (IDresponsable)
);
create table formule (
num_formule int not null auto_increment,
description varchar (30),
PRIMARY KEY (num_formule)
);

USE gestion_formules;

CREATE TABLE formules (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom_formule VARCHAR(100),
    prix_formule DOUBLE,
    nb_cours INT
);




create table mois(
    idannee_mois VARCHAR(7) NOT NULL PRIMARY KEY, 
    mois VARCHAR(20)
);
create table km_effectuer (
IDkm_effectuer int not null auto_increment,
IDVEHICULE int(3) not null ,
idannee_mois VARCHAR(7) not null,
kilometrage  int(3) not null,
PRIMARY KEY (IDkm_effectuer),
constraint fk_vehicule foreign key (IDVEHICULE) references VEHICULE(IDVEHICULE),
constraint fk_anneemois foreign key (idannee_mois) references mois(idannee_mois)
);
create table passage_examen (
idpassage_examen int not null auto_increment,
IDEXAMEN int(11) not null,
idcandidat int(1) not null,
date_et_heure_examen DATETIME NOT NULL,
PRIMARY KEY (idpassage_examen),
constraint fkr_examen foreign key (IDEXAMEN) references EXAMEN(IDEXAMEN),
constraint fkt_candidat foreign key (idcandidat) references candidat(idcandidat)
);
CREATE TABLE USER
 (
   IDUSER int(3) NOT NULL auto_increment,
   PHOTO VARCHAR(100) NULL  ,
   NOM VARCHAR(32) NULL ,
   PRENOM VARCHAR(32) NULL ,
   EMAIL VARCHAR(32) NULL  ,
    MDP VARCHAR(32) NULL, 
   type_user enum("admin","user","candidat","moniteur","responsable"),
     NUMERO_TELEPHONE VARCHAR(32) NULL,
     PRIMARY KEY (IDUSER) 
 );
 insert into responsable values('','','jimy','jade','p@gmail.com','G11','responsable','05437455
3');

 DELIMITER //

CREATE FUNCTION generer_mot_de_passe_sql(prenom VARCHAR(30), nom VARCHAR(30))
RETURNS VARCHAR(50)
DETERMINISTIC
BEGIN
    DECLARE premiere_lettre_prenom VARCHAR(1);
    DECLARE trois_premieres_lettres_nom VARCHAR(3);
    DECLARE date_aujourdhui VARCHAR(8);
    DECLARE mot_de_passe VARCHAR(50);

    IF prenom IS NULL OR TRIM(prenom) = '' OR nom IS NULL OR TRIM(nom) = '' THEN
        RETURN NULL;
    END IF;

    SET premiere_lettre_prenom = SUBSTRING(prenom, 1, 1);

    IF LENGTH(nom) >= 3 THEN
        SET trois_premieres_lettres_nom = SUBSTRING(nom, 1, 3);
    ELSE
        SET trois_premieres_lettres_nom = nom;
    END IF;

    SET date_aujourdhui = DATE_FORMAT(CURDATE(), '%Y%m%d');

    SET mot_de_passe = CONCAT(premiere_lettre_prenom, '.', trois_premieres_lettres_nom, date_aujourdhui);

    RETURN mot_de_passe;
END //

DELIMITER ;




DELIMITER //

CREATE TRIGGER avant_insertion_responsable
BEFORE INSERT ON responsable
FOR EACH ROW
BEGIN
    SET NEW.mdp = generer_mot_de_passe_sql(NEW.prenom, NEW.nom);
END //

DELIMITER ;