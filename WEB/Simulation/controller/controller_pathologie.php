<?php

require_once('model/pathologie/pathologie_bdd.php');
require_once('controller/controllerTwig.php');




class Pathologie extends ControllerTwig{

    public function listsymptome(){

        $pathologie = new Pathologie_BDD();
        $result = $pathologie->Pathologie_vers_Symptome();
        if($result != null){
            $this->renderWithParams('listsymptome', ['result' => $result]);
        }
        else{
            $this->render('acceuil');
        }
        

       
    }

    public function recherchesymptome($symptome){

        $pathologie = new Pathologie_BDD();
        $result = $pathologie->Recherche_Symptome_vers_Pathologie($symptome);
        if($result != null){
            $this->renderWithParams('recherche_patho', ['result' => $result]);
        }
        else{

            $erreur = "Aucun résultat";
            $this->renderWithParams('recherche_patho', ['erreur' => $erreur]);
        }
        

       
    }

    public function filtrepatho($meridien, $caracteristique, $type){

        $pathologie = new Pathologie_BDD();
        $result = $pathologie->FiltreMeridien($meridien, $caracteristique, $type);
        if($result != null){
            $this->renderWithParams('filtre_patho', ['result' => $result[0], 'meridien_all' => $result[1], 'caracteristique_all' => $result[2], 'type_all' => $result[3], 'meridienselected' => $result[4], 'caracteristiqueselected' => $result[5], 'typeselected' => $result[6]]);
        }
        else{

            $erreur = "Aucun résultat";
            $this->renderWithParams('filtre_patho', ['erreur' => $erreur]);
        }
        

       
    }

   
        

  

}

?>

