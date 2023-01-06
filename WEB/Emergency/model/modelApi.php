<?php
require_once('model/Model.php');
class modelApi extends Model{
    public function __construct(){
        $this->getConnection();
    }
    public function getid($parametre3){
        $query="select p.desc from meridien  inner join patho p ON p.mer=meridien.code where idp= :idp";
        $stmt = $this->bdd->prepare($query);
        $stmt->execute(array(
            'idp' => $parametre3
        ));
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
    }
    public function getall(){
        $query="select p.desc from meridien  inner join patho p ON p.mer=meridien.code";
        $stmt = $this->bdd->prepare($query);
        $stmt->execute();
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
    }
}