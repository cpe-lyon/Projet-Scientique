<?php 

require_once('model/Model.php');

class Emergency_BDD  extends Model{

    public function __construct(){
        $this->getConnection();
        
    }   


    public function getEmergency(){
        $query = "SELECT * FROM lieux";
        $stmt = $this->bdd->prepare($query);
        $stmt->execute();
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
            
        }
}