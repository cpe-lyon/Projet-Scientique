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

    public function Emergency_Nb_Interventions(){
        $query = "SELECT COUNT(*) AS nb_interventions FROM interventions where etat = 0";
        $stmt = $this->bdd->prepare($query);
        $stmt->execute();
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
    }

    public function Emergency_Nb_Feux(){
        $query = "SELECT COUNT(*) AS nb_feux FROM lieux WHERE intensite > 4 AND intensite < 10 ";
        $stmt = $this->bdd->prepare($query);
        $stmt->execute();
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
    }

    public function Emergency_Camion_Dispo(){
        $query = "SELECT COUNT(*) AS nb_camion FROM camions WHERE etat = 0";
        $stmt = $this->bdd->prepare($query);
        $stmt->execute();
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
    }

    public function Emergency_Nb_Feux_Interventions(){
        $query = "SELECT COUNT(*) AS nb_feux_interventions FROM Interventions INNER JOIN LIEUX ON interventions.lieu = lieux.id and interventions.etat = 1";
        $stmt = $this->bdd->prepare($query);
        $stmt->execute();
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
    }
}