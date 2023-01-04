<?php

require_once('model/pathologie/emergency_bdd.php');
require_once('controller/controllerTwig.php');


class Emergency extends ControllerTwig{
    public function getEmergency(){
        $emergency = new Emergency_BDD();
        $result = $emergency->getEmergency();
        if($result != null){
            $this->renderWithParams('emergency', ['result' => $result]);
        }
        else{
            $this->render('acceuil');
        }
        
    }
}



