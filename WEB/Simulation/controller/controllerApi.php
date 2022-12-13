<?php
require_once('controller/controllerTwig.php');
require_once('model/modelApi.php');
class APIcontroller extends ControllerTwig{
    public function __construct(){
        parent::__construct();
    }
    public function getid($parametre3){
        $parametre3 =htmlspecialchars($parametre3);
        $modelApi = new modelApi();
        $result = $modelApi->getid($parametre3);
        echo json_encode($result,JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
        http_response_code(200);
        header('Content-Type: application/json');
    }
    public function getAll(){
        $modelApi = new modelApi();
        $result = $modelApi->getall();
        echo json_encode($result,JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
        http_response_code(200);
        header('Content-Type: application/json');
    }
    public function error(){
        http_response_code(404);
    }

}