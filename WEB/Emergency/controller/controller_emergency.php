<?php

require_once('model/pathologie/Emergency_BDD.php');
require_once('controller/controllerTwig.php');


class Emergency extends ControllerTwig{

   

    public function Emergency_Localisation_Feux(){
        $emergency = new Emergency_BDD();
        $result = $emergency->Emergency_Localisation_Feux_BDD();
        $camion = $emergency->Emergency_Localisation_Camion_BDD();
        $nb = $emergency->Emergency_Nb_Interventions();
        $total_feux = $emergency->Emergency_Nb_Feux();
        $camion_dispo = $emergency->Emergency_Camion_Dispo();
        $nb_feux_interventions = $emergency->Emergency_Nb_Feux_Interventions();
        if($result != null || $camion != null){
            $this->renderWithParams('acceuil', ['result' => $result, 'camion' => $camion, 'nb' => $nb, 'total_feux' => $total_feux, 'camion_dispo' => $camion_dispo, 'nb_feux_interventions' => $nb_feux_interventions]);
        }
        else{
            $this->render('acceuil');
        }
        
    }



    public function map(){
        Emergency_Localisation_Feux();
        Emergency_Localisation_Camion();
        Emergency_Nb_Interventions();
    }
}



