<?php
require_once('controller/controllerTwig.php');
require_once('model/login.php');
class Index extends ControllerTwig
{
    public function __construct()
    {
        parent::__construct();
    }

    
    public function veriflogin($mail, $password)
    {
        $login = new login();
        if ($login->login($mail, $password) == true) {
            
            session_start();

            $_SESSION['email'] = $mail;
            $_SESSION['password'] = $password;
            $_SESSION['connected'] = true;

            echo $this->render('acceuil_log');


        } else {
            $erreur = "Identifiant ou mot de passe incorrect";
            echo $this->renderWithParams('acceuil', ['erreur' => $erreur]);
        }
    }
    public function register($mail, $password, $confirm)
    {
        $login = new login();
        if ($password == $confirm) {
            if ($login->register($mail, $password) == true) {
                $erreur = "Inscription réussie";
                echo $this->renderWithParams('acceuil', ['erreur' => $erreur]);
            } else {
                $erreur = "Adresse mail déjà utilisée";
                echo $this->renderWithParams('inscription', ['erreur' => $erreur]);
            }
        } else {
            $erreur = "Les mots de passe ne correspondent pas";
            echo $this->renderWithParams('inscription', ['erreur' => $erreur]);
        }
        
    }



    public function logout()
    {
        session_start();
        $_SESSION['email'] = null;
        session_destroy();
        echo $this->render('acceuil');
    }

    public function login()
    {
        echo $this->render('acceuil');
    }

}
