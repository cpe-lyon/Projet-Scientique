<?php 

require_once('model/Model.php');

class Emergency_BDD  extends Model{

    public function __construct(){
        $this->getConnection();
        
    }   


    public function Emergency_Localisation_Feux_BDD(){
        $query = "SELECT * FROM lieux";
        $stmt = $this->bdd->prepare($query);
        $stmt->execute();
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
            
    }


    public function Emergency_Localisation_Camion_BDD(){
        $query = "SELECT * FROM camions";
        $stmt = $this->bdd->prepare($query);
        $stmt->execute();
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
    }

    public function Reset(){
        $query = "
        -- création des tables --
        
        DROP TABLE IF EXISTS public.casernes CASCADE;
        DROP TABLE IF EXISTS public.camions CASCADE;
        DROP TABLE IF EXISTS public.equipes CASCADE;
        DROP TABLE IF EXISTS public.lieux CASCADE;
        DROP TABLE IF EXISTS public.interventions CASCADE;
        DROP TABLE IF EXISTS public.personnel CASCADE;
        DROP TABLE IF EXISTS public.equipe_personne CASCADE;
        
        -- création des tables --
        
        
        
        CREATE TABLE public.casernes
        (
            id integer NOT NULL,
            camions integer NOT NULL,
            equipes integer NOT NULL,
            PRIMARY KEY (id)
        );
        
        CREATE TABLE public.camions
        (
            id integer NOT NULL,
            etat integer NOT NULL,
            citerne integer NOT NULL,
            Position_Y integer NOT NULL,
            Position_X integer NOT NULL,
            PRIMARY KEY (id)
        );
        
        CREATE TABLE public.equipes
        (
            id integer NOT NULL,
            num_equipe integer NOT NULL,
            personnel integer NOT NULL,
            etat integer NOT NULL,
            PRIMARY KEY (id)
        );
        
        
        CREATE TABLE public.lieux
        (
            id integer NOT NULL,
            Adresse_Y integer NOT NULL,
            Adresse_X integer NOT NULL,
            Intensite double precision NOT NULL,
            PRIMARY KEY (id)
        );
        
        CREATE TABLE public.interventions
        (
            id integer NOT NULL,
            equipe integer NOT NULL,
            camion int NOT NULL,
            lieu int NOT NULL,
            etat int NOT NULL,
            PRIMARY KEY (id)
        );
        
        CREATE TABLE public.personnel
        (
            id integer NOT NULL,
            etat varchar NOT NULL,
            PRIMARY KEY (id)
        );
        
        
        
        
        -- ajout des contraintes --
        
        -- Casernes Equpe Camion Lieux --
        
        ALTER TABLE IF EXISTS public.casernes
        ADD CONSTRAINT id_camion_fk FOREIGN KEY (camions) REFERENCES public.camions(id);
        ALTER TABLE IF EXISTS public.casernes
        ADD CONSTRAINT id_equipe_fk FOREIGN KEY (equipes) REFERENCES public.equipes(id);
        
        
        
        -- Equipes Personnel --
        ALTER TABLE IF EXISTS public.equipes 
        ADD CONSTRAINT id_personnel_fk FOREIGN KEY (personnel) REFERENCES public.personnel(id);
        
        
        -- Interventions Equipe Camion Lieu --
        
        ALTER TABLE IF EXISTS public.interventions
        ADD CONSTRAINT id_equipe_fk FOREIGN KEY (equipe) REFERENCES public.equipes(id);
        ALTER TABLE IF EXISTS public.interventions
        ADD CONSTRAINT id_camion_fk FOREIGN KEY (camion) REFERENCES public.camions(id);
        ALTER TABLE IF EXISTS public.interventions
        ADD CONSTRAINT id_lieu_fk FOREIGN KEY (lieu) REFERENCES public.lieux(id);
        
        
        
        CREATE TABLE equipe_personne (
            equipe_id INT NOT NULL,
            personne_id INT NOT NULL,
            FOREIGN KEY (equipe_id) REFERENCES equipes (id) ON DELETE RESTRICT ON UPDATE CASCADE,
            FOREIGN KEY (personne_id) REFERENCES personnel (id) ON DELETE RESTRICT ON UPDATE CASCADE,
            PRIMARY KEY (equipe_id, personne_id)
        );
        
        
        -- METTRE LES CLEFS ETRANGERES AUX TABLES ! --
        
        
        -- ajout de données --
        
        insert into camions values (1,0,3, 0, 0);
        insert into camions values (2,0,3, 0, 0);
        insert into camions values (3,0,3, 0, 0);
        insert into camions values (4,0,3, 0, 0);
        insert into camions values (5,0,3, 0, 0);
        
        
        insert into personnel values (1,'Dispo');
        insert into personnel values (2,'Dispo');
        insert into personnel values (3,'Dispo');
        insert into personnel values (4,'Dispo');
        insert into personnel values (5,'Dispo');
        insert into personnel values (6,'Dispo');
        insert into personnel values (7,'Dispo');
        insert into personnel values (8,'Dispo');
        insert into personnel values (9,'Dispo');
        insert into personnel values (10,'Dispo');
        
        
        
        insert into lieux values (1, 0, 0, 0);
        insert into lieux values (2, 1, 0, 0);
        insert into lieux values (3, 2, 0, 0);
        insert into lieux values (4, 3, 0, 0);
        insert into lieux values (5, 4, 0, 0);
        insert into lieux values (6, 5, 0, 0);
        insert into lieux values (7, 6, 0, 0);
        insert into lieux values (8, 7, 0, 0);
        insert into lieux values (9, 8, 0, 0);
        insert into lieux values (10, 9, 0, 0);
        insert into lieux values (11, 0, 1, 0);
        insert into lieux values (12, 1, 1, 0);
        insert into lieux values (13, 2, 1, 0);
        insert into lieux values (14, 3, 1, 0);
        insert into lieux values (15, 4, 1, 0);
        insert into lieux values (16, 5, 1, 0);
        insert into lieux values (17, 6, 1, 0);
        insert into lieux values (18, 7, 1, 0);
        insert into lieux values (19, 8, 1, 0);
        insert into lieux values (20, 9, 1, 0);
        insert into lieux values (21, 0, 2, 0);
        insert into lieux values (22, 1, 2, 0);
        insert into lieux values (23, 2, 2, 0);
        insert into lieux values (24, 3, 2, 0);
        insert into lieux values (25, 4, 2, 0);
        insert into lieux values (26, 5, 2, 0);
        insert into lieux values (27, 6, 2, 0);
        insert into lieux values (28, 7, 2, 0);
        insert into lieux values (29, 8, 2, 0);
        insert into lieux values (30, 9, 2, 0);
        insert into lieux values (31, 0, 3, 0);
        insert into lieux values (32, 1, 3, 0);
        insert into lieux values (33, 2, 3, 0);
        insert into lieux values (34, 3, 3, 0);
        insert into lieux values (35, 4, 3, 0);
        insert into lieux values (36, 5, 3, 0);
        insert into lieux values (37, 6, 3, 0);
        insert into lieux values (38, 7, 3, 0);
        insert into lieux values (39, 8, 3, 0);
        insert into lieux values (40, 9, 3, 0);
        insert into lieux values (41, 0, 4, 0);
        insert into lieux values (42, 1, 4, 0);
        insert into lieux values (43, 2, 4, 0);
        insert into lieux values (44, 3, 4, 0);
        insert into lieux values (45, 4, 4, 0);
        insert into lieux values (46, 5, 4, 0);
        insert into lieux values (47, 6, 4, 0);
        insert into lieux values (48, 7, 4, 0);
        insert into lieux values (49, 8, 4, 0);
        insert into lieux values (50, 9, 4, 0);
        insert into lieux values (51, 0, 5, 0);
        insert into lieux values (52, 1, 5, 0);
        insert into lieux values (53, 2, 5, 0);
        insert into lieux values (54, 3, 5, 0);
        insert into lieux values (55, 4, 5, 0);
        insert into lieux values (56, 5, 5, 0);
        insert into lieux values (57, 6, 5, 0);
        insert into lieux values (58, 7, 5, 0);
        insert into lieux values (59, 8, 5, 0);
        insert into lieux values (60, 9, 5, 0);
        
        insert into Equipes values(1,1,1, 0);
        insert into Equipes values(2,1,2, 0);
        insert into Equipes values(3,2,3, 0);
        insert into Equipes values(4,2,4, 0);
        insert into Equipes values(5,3,5, 0);
        insert into Equipes values(6,3,6, 0);
        insert into Equipes values(7,4,7, 0);
        insert into Equipes values(8,4,8, 0);
        insert into Equipes values(9,5,9, 0);
        insert into Equipes values(10,5,10, 0);
        
        
        insert into Equipe_Personne values(1,1);
        insert into Equipe_Personne values(1,2);
        insert into Equipe_Personne values(2,3);
        insert into Equipe_Personne values(2,4);
        insert into Equipe_Personne values(3,5);
        insert into Equipe_Personne values(3,6);
        insert into Equipe_Personne values(4,7);
        insert into Equipe_Personne values(4,8);
        insert into Equipe_Personne values(5,9);
        insert into Equipe_Personne values(5,10);
        
        
        insert into Casernes values(1,1,1);
        insert into Casernes values(2,2,1);
        insert into Casernes values(3,3,1);
        insert into Casernes values(4,4,1);
        insert into Casernes values(5,5,1);
        
        
        
        
        
        -- requetes --
        
        -- Affiche les personnes d'une equipe --
        
        -- ep.equipe_id = 1 or 2 or 3 or 4 or 5  car il y a 5 equipes --
        
        -- select * from equipes e inner join equipe_personne ep on e.id = ep.equipe_id inner join personnel p on ep.personne_id = p.id where ep.equipe_id = 1;";

        $stmt = $this->pdo->prepare($sql);
        $stmt->execute();
        $result = $stmt->fetchAll();
        return $result;

    }
}