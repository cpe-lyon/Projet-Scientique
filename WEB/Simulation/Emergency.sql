
-- création des tables --


CREATE TABLE public.casernes
(
    id_caserne integer NOT NULL,
    camions integer NOT NULL,
    equipes integer NOT NULL,
    lieux integer NOT NULL,
    PRIMARY KEY (id_caserne)
);

CREATE TABLE public.camions
(
    id_camion integer NOT NULL,
    etat varchar NOT NULL,
    citerne integer NOT NULL,
    PRIMARY KEY (id_camion)
);

CREATE TABLE public.equipes
(
    id_equipe integer NOT NULL,
    personnel integer NOT NULL,
    etat varchar NOT NULL,
    PRIMARY KEY (id_equipe)
);

CREATE TABLE public.evenements
(
    id_evenement integer NOT NULL,
    intensite integer,
    categorie_evenement varchar NOT NULL,
    PRIMARY KEY (id_evenement)
);

CREATE TABLE public.lieux
(
    id_lieu integer NOT NULL,
    evenement integer NOT NULL,
    adresse varchar NOT NULL,
    code_postal integer NOT NULL,
    PRIMARY KEY (id_lieu)
);

CREATE TABLE public.interventions
(
    id_intervention integer NOT NULL,
    equipe integer NOT NULL,
    camion int NOT NULL,
    lieu int NOT NULL,
    evenement integer NOT NULL,
    PRIMARY KEY (id_intervention)
);

CREATE TABLE public.personnel
(
    id_personnel integer NOT NULL,
    etat varchar NOT NULL,
    PRIMARY KEY (id_personnel)
);


ALTER TABLE IF EXISTS public.casernes
    CONSTRAINT 

ALTER TABLE IF EXISTS public.evenements
    add temps time


-- METTRE LES CLEFS ETRANGERES AUX TABLES ! --


-- ajout de données --

insert into camions values ('1','dispo','véhicule de secours et d’assistance aux victimes (VSAV)');
insert into camions values ('2','dispo','véhicule secours routier (VSR)');
insert into camions values ('3','dispo','véhicule secours routier (VSR)');
insert into camions values ('4','dispo','La grande échelle, ou échelle pivotante automatique (EPA)');
insert into camions values ('5','dispo','Le fourgon pompe-tonne (FPT)');
insert into camions values ('6','dispo','véhicule de secours et d’assistance aux victimes (VSAV)');


insert into personnel values ('101','dispo');
insert into personnel values ('102','dispo');
insert into personnel values ('103','dispo');
insert into personnel values ('201','dispo');
insert into personnel values ('202','dispo');
insert into personnel values ('203','dispo');
insert into personnel values ('301','dispo');
insert into personnel values ('302','dispo');
insert into personnel values ('303','dispo');
insert into personnel values ('401','dispo');
insert into personnel values ('402','dispo');
insert into personnel values ('403','dispo');
insert into personnel values ('501','dispo');
insert into personnel values ('502','dispo');
insert into personnel values ('503','dispo');
insert into personnel values ('601','dispo');
insert into personnel values ('602','dispo');
insert into personnel values ('603','dispo');



{
  "type": "FeatureCollection",
  "features": [
    {
      "type": "Feature",
      "properties": {},
      "geometry": {
        "coordinates": [
          4.8503674914480825,
          45.77002787260963
        ],
        "type": "Point"
      }
    },
    {
      "type": "Feature",
      "properties": {},
      "geometry": {
        "coordinates": [
          0,
          0
        ],
        "type": "Point"
      }
    },
    {
      "type": "Feature",
      "properties": {},
      "geometry": {
        "coordinates": [
          0,
          1
        ],
        "type": "Point"
      }
    },
    {
      "type": "Feature",
      "properties": {},
      "geometry": {
        "coordinates": [
          0,
          2
        ],
        "type": "Point"
      }
    },
    {
      "type": "Feature",
      "properties": {},
      "geometry": {
        "coordinates": [
          0,
          3
        ],
        "type": "Point"
      }
    },
    {
      "type": "Feature",
      "properties": {},
      "geometry": {
        "coordinates": [
          0,
          4
        ],
        "type": "Point"
      }
    },
    {
      "type": "Feature",
      "properties": {},
      "geometry": {
        "coordinates": [
          4.864473200108961,
          45.76857284064309
        ],
        "type": "Point"
      }
    },
    {
      "type": "Feature",
      "properties": {},
      "geometry": {
        "coordinates": [
          0,
          5
        ],
        "type": "Point"
      }
    },
    {
      "type": "Feature",
      "properties": {},
      "geometry": {
        "coordinates": [
          0,
          6
        ],
        "type": "Point"
      }
    }
    {
        
    }
  ]
}

