<?php

require_once('model/pathologie/Emergency_BDD.php');
require_once('controller/controllerTwig.php');


class Emergency extends ControllerTwig{

   

    public function Emergency_Localisation_Feux(){
        $emergency = new Emergency_BDD();
        $result = $emergency->Emergency_Localisation_Feux_BDD();
        $camion = $emergency->Emergency_Localisation_Camion_BDD();
        if($result != null || $camion != null){
            $this->renderWithParams('acceuil', ['result' => $result, 'camion' => $camion]);
        }
        else{
            $this->render('acceuil');
        }
        
    }



    public function map(){
        Emergency_Localisation_Feux();
        Emergency_Localisation_Camion();
    }
}



