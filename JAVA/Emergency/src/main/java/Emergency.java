

import static java.lang.Thread.sleep;

public class Emergency {
    public static void main(String[] args) throws InterruptedException {
        String url = "jdbc:postgresql://localhost:5432/emergency";
        String user = "pgtp";
        String pwd = "tp";
        BDD bdd = new BDD(url,user,pwd);
        float intensite = 0;
        int posX, posY;
        boolean is_in_intervention;
        int id_camion = 0;
        int nb_intervention = 1;
        while(true) //parcourt la bdd
        {
            for (int i=1; i<61; i++)
            {
                intensite = bdd.rpg3(i); //recuperation id

                if (intensite > 4 && intensite < 10) // si feu il y a
                {

                    is_in_intervention = bdd.rpg6(i);//check si une intervention à lieu sur ce lieu
                    //pos du lieu
                    posX = bdd.rpg4(i);
                    posY = bdd.rpg44(i);

                    if(!is_in_intervention) //si non
                    {
                        id_camion = bdd.rpg7(); //récupération id camion libre

                        if (id_camion != 0)//si il y a un camion libre
                        {
                            bdd.bazooka("INSERT INTO interventions VALUES ("+nb_intervention+",1,"+id_camion+","+i+",0)");
                            bdd.bazooka("UPDATE camions SET etat = 1 WHERE id = "+id_camion); //met le camion en occupé
                            nb_intervention++;
                        }

                    }

                }


            }
            sleep(4000);
        }
    }
}