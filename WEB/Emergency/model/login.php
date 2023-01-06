<?php
require_once('model/Model.php');
class login extends Model
{
    public function __construct()
    {
        $this->table = 'utilisateur';
        $this->getConnection();
    }
    public function login($login, $password)
    {
        $sql = "SELECT * FROM $this->table WHERE email = :email AND password = :password";
        $stmt = $this->bdd->prepare($sql);
        $stmt->execute(array(
            'email' => $login,
            'password' => $password
        ));
        $count = $stmt->rowCount();
        if($count == 1)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    public function register($mail, $password){
        $check = "SELECT * FROM $this->table WHERE email = :email";
        $req=$this->bdd->prepare($check);
        $req->execute(array(
            'email' => $mail
        ));
        $count = $req->rowCount();
        if($count == 0){
            $sql = "INSERT INTO $this->table (email, password) VALUES (:email, :password)";
            $stmt = $this->bdd->prepare($sql);
            $stmt->execute(array(
                'email' => $mail,
                'password' => $password
            ));
            return true;
        }
        else{
            return false;
        }
    }
}