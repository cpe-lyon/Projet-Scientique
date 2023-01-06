<?php
class Model{
    private $host = 'localhost';
    private $dbname = 'emergency';
    private $user = 'pgtp';
    private $password = 'tp';
    protected $bdd;

    public $table;
    public $id;

    public function getConnection(){
        $this->bdd = null;
        try{
            $this->bdd = new PDO('pgsql:host=' . $this->host . ';dbname=' . $this->dbname, $this->user, $this->password);
        }catch(PDOException $exception){
            echo "Connection error: " . $exception->getMessage();
        }
        return $this->bdd;
    }
}