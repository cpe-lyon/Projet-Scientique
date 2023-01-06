
<?php

require_once('model/Model.php');

class Pathologie_BDD extends Model{

    public function __construct(){
        $this->getConnection();
        
    }   
   
    public function Pathologie_vers_Symptome(){
        $query = "select p.desc as descp, sy.desc as descsy from patho p inner join symptpatho sp ON p.idp = sp.idp inner join symptome sy ON sy.ids = sp.ids order by p.desc ASC  ";
        $stmt = $this->bdd->prepare($query);
        $stmt->execute();
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
            
        }

    public function Recherche_Symptome_vers_Pathologie($symptome){
               
        $query = "SELECT p.desc as patho_desc, s.desc as symptome_desc FROM patho p INNER JOIN symptPatho sp ON  p.idP = sp.idP INNER JOIN symptome s ON sp.idS = s.idS WHERE translate(UPPER(s.desc), 'ÉÈÊËÔ','EEEEO' ) LIKE  translate(upper('%$symptome%'), 'ÉÈÊËÔ','EEEEO')  ";
        $stmt = $this->bdd->prepare($query);
        $stmt->execute();
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        return $result;
    }

    public function FiltreMeridien($meridien, $caracteristique, $type){


        if(isset($meridien) ){
                            
            $x = count($meridien);
            $i = 0;
                    
            foreach($meridien as $meridienvar){
        
           
                if ($meridienvar != "") {
                    if ($i == 0) {
                        $meridiensql .= "WHERE (";
                    }
                    
                    $meridiensql .=  "nom = '" . $meridienvar . "'";
                    
                    if ($i < $x-1){
                        $meridiensql .=  " OR ";
                            
                    }
        
                    if ($i == $x-1){
                        $meridiensql .=  ")";
                            
                    }
        
                    $i += 1;   
                }  
                
                
            }

         
        
        }if (isset($type) ){
        
            
        
                $x = count($type);
                $i = 0;
        
                foreach($type as $typevar){
                    if ($typevar != "") {
                        if ($i == 0) {
        
                            if ($meridiensql != "") {
                                $meridiensql .= " AND (";
                            }else {
                                $meridiensql .= " WHERE (";
                            }
                            
                        }
        
                        $meridiensql .=  "description = '" . $typevar . "'";
        
                        if ($i < $x-1){
                            $meridiensql .=  " OR ";
                            
                        }
        
                        if ($i == $x-1){
                            $meridiensql .=  ")";
                            
        
                        }
        
                        $i += 1;
                    }
        
                
                }
        }if (isset($caracteristique) ){
        
            $x = count($caracteristique);
            $i = 0;
        
            foreach($caracteristique as $caracteristiquevar){
                if ($caracteristiquevar != "") {
                    if ($i == 0) {
                        if ($meridiensql != ""){
                            $meridiensql .= "AND (";   
                        }
                        else {
                            $meridiensql .= "WHERE (";
                        }
                       
                    }
        
                    $meridiensql .=  "caracteristique = '" . $caracteristiquevar . "'";
        
                    if ($i < $x-1){
                        $meridiensql .=  " OR ";
                        
        
                    }
        
                    if ($i == $x-1){
                        $meridiensql .=  ")";
                        
        
                    }
        
                    $i += 1;
        
                }
        
            }
        
        
        }

        $query_meridien = "select meridien.nom, p.desc,  d.caracteristique from meridien  inner join patho p ON p.mer=meridien.code  inner join description d ON p.type = d.type_patho $meridiensql" ;      
        $stmt = $this->bdd->prepare($query_meridien);
        $stmt->execute();
        $result = $stmt->fetchAll(PDO::FETCH_ASSOC);

        $query = "select distinct nom from meridien m INNER JOIN PATHO p ON m.code = p.mer INNER JOIN DESCRIPTION d ON p.type = d.type_patho $meridiensql";
        $stmt = $this->bdd->prepare($query);
        $stmt->execute();
        $result_meridien = $stmt->fetchAll(PDO::FETCH_ASSOC);

        $query_caracteristique = "select distinct caracteristique from meridien  inner join patho p ON p.mer=meridien.code  inner join description d ON p.type = d.type_patho $meridiensql" ;
        $stmt = $this->bdd->prepare($query_caracteristique);
        $stmt->execute();
        $result_caracteristique = $stmt->fetchAll(PDO::FETCH_ASSOC);


        $query_type = "select distinct description from meridien  inner join patho p ON p.mer=meridien.code  inner join description d ON p.type = d.type_patho $meridiensql" ;
        $stmt = $this->bdd->prepare($query_type);
        $stmt->execute();
        $result_type = $stmt->fetchAll(PDO::FETCH_ASSOC);


        return [$result, $result_meridien, $result_caracteristique, $result_type, $meridien, $caracteristique, $type];

    }
}
?>