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
}