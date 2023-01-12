<?php
//routeur de notre site WEB SEP
include_once('controller/controllerIndex.php');
include_once('controller/controller_pathologie.php');
include_once('controller/controllerApi.php');
include_once('controller/controller_emergency.php');

$index = new Index();  // instanciation de la classe Index
$patho = new Pathologie(); // instanciation de la classe Pathologie
$api = new APIcontroller(); // instanciation de la classe APIcontroller
$emergency = new Emergency(); // instanciation de la classe Emergency
$page= $index->getUrl(); // récupère l'url de la page




session_start();  // On démarre la session AVANT toute chose

if($page == 'veriflogin'){
    $index->veriflogin($_POST['email'], $_POST['password']);  // vérifie si l'utilisateur est connecté
}
elseif ($page == "deconnexion"){
    $index->logout(); // déconnexion
}
elseif ($page == "login"){
    $index->login(); // page de connexion
}
elseif ($page == "listsymptome"){
    $patho -> listsymptome(); // page de liste des symptomes
}
elseif ($page == "recherche_patho"){
    if (isset($_SESSION['email'])){        
        $patho -> recherchesymptome($_POST['patho']); // page de recherche des pathologies
    }else {
        $index->login(); // page de connexion
    }  
}
elseif($page == "filtre_patho"){
    $patho -> filtrepatho($_POST['meridien'], $_POST['caracteristique'], $_POST['type']); // page de filtre des pathologies
}
elseif($page == 'register'){
    $index->register($_POST['email'], $_POST['password'], $_POST['confirm']); // page d'inscription
}
elseif($_SERVER['REQUEST_METHOD'] == 'GET'){
    $url_components = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);
    $param = explode('/', $url_components);
    $parametre1 = $param[2];
    $parametre2 = $param[3];
    $parametre3 = $param[4];
    if($parametre1=="res" && $parametre2=="patho"){
        if($parametre3!= null){
            $api->getid($parametre3);
        }
        if($parametre3== null){
            $api->getAll();
        }
        else{
            $api->error();
        }
    
}elseif($page == "acceuil"){
    $emergency -> Emergency_Localisation_Feux();
}
elseif($page == "reset"){
    $emergency->reset(); // page de réinitialisation du mot de passe
}
else{
        if (isset($_SESSION['email']) and $page=="acceuil"){
            $index->render('acceuil_log'); // page d'accueil si l'utilisateur est connecté
        }else {
        $index->render($page);
        }
    }
}