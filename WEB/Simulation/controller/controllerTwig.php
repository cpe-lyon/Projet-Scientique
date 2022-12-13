<?php
require_once('vendor/autoload.php');

class ControllerTwig
{
    public function __construct()
    {
        $loader = new Twig\Loader\FilesystemLoader('templates');
        $twig = new Twig\Environment($loader);
        $this->twig = $twig;
    }
    public function getUrl()
    {
        if (isset($_GET['p'])) {
            $url = $_GET['p'];
            return $url;
        } else {
            return 'acceuil';
        }
    }


    

    public function renderWithParams($page, $param)
    {
        if (isset($_SESSION['email'])) {
            $tab = array('session' => $_SESSION);
            echo $this->twig->render($page . '.html.twig', array_merge($param, $tab));
        }
        else{
            echo $this->twig->render($page . '.html.twig', $param);
        }
    }
    public function render($page)
    {
        if (isset($_SESSION['email'])) {
            echo $this->twig->render($page . '.html.twig', ['session' => $_SESSION]);
        } 
        else {

           
            echo $this->twig->render($page . '.html.twig', );
        }
    }
}
