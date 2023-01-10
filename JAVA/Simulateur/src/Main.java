
import java.sql.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Main
{
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        /**
        String dbURL2 = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String pass = "admin";
        Connection conn2 = null;
        String query = "SELECT * from camions";
        try {
            conn2 = DriverManager.getConnection(dbURL2, user, pass);
            if (conn2 != null)
            {
                System.out.println("Connected to database #2");
                Statement stmt = conn2.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * from camions");
                while ( rs.next() ) {
                    System.out.println(rs.getString("etat"));
                }
                conn2.close();
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                if (conn2 != null && !conn2.isClosed())
                {
                    conn2.close();
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        **/
        //connexion bdd
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String pwd = "admin";
        BDD bdd = new BDD(url,user,pwd);

        //création des camion
        Camion[] listCamion = new Camion[5];
        int posxCaserne = 0;
        int posyCaserne = 0;
        for (int a = 0; a<5; a++)
        {
            Camion camion = new Camion(bdd.rpg8(a+1),bdd.rpg9(a+1),0,0);
            listCamion[a]=camion;
        }

        Feu[] listFire = new Feu[60];
        int cpt = 0;

        Grid grid = new Grid(6,10);

        float[][] grille = grid.gridCreation(bdd);

        System.out.println(grid.toString());

        Feu feu = new Feu();
        listFire[cpt] = feu;
        cpt = feu.putFire(grille,cpt,bdd);
        Feu feu1 = new Feu();
        listFire[cpt] = feu1;
        cpt = feu1.putFire(grille,cpt,bdd);
        Feu feu2 = new Feu();
        listFire[cpt] = feu2;
        cpt = feu2.putFire(grille,cpt,bdd);
        Feu feu3 = new Feu();
        listFire[cpt] = feu3;
        cpt = feu3.putFire(grille,cpt,bdd);
        Feu feu4 = new Feu();
        listFire[cpt] = feu4;
        cpt = feu4.putFire(grille,cpt,bdd);

        Feu feu5 = new Feu();
        listFire[cpt] = feu5;
        cpt = feu5.putFire(grille,cpt,bdd);
        Feu feu6 = new Feu();
        listFire[cpt] = feu6;
        cpt = feu6.putFire(grille,cpt,bdd);

        System.out.println("@@@@@@@@@@@@@@@@@@@@");
        System.out.println(bdd.rpg(feu.getPosx(), feu.getPosy()));
        System.out.println("@@@@@@@@@@@@@@@@@@@@");

        for (int i=0; i<100; i++) //tours
        {
            System.out.println("Début Tour " + i);
            System.out.println("Nb de feu : " + cpt);

            for (int j=0; j<cpt; j++)//le feu se propage
            {
                //System.out.println(listFire[j].toString());
                cpt = listFire[j].increaseIntensite(grille, listFire, cpt, bdd);
            }

            for (int k = 1; k < 6; k++)
            {
                if(bdd.rpg10(k)==1) //si camion assigné a une intervention mais qu'il n'est pas partis
                {
                    int id_lieu = bdd.rpg11(k);//recuperer id lieu correspondant à la destination de ce camion
                    int posXlieu = bdd.rpg4(id_lieu);
                    int posYlieu = bdd.rpg44(id_lieu);
                    bdd.bazooka("UPDATE camions SET etat = 2 WHERE id ="+k);
                }
                else if (bdd.rpg10(k)==2)//en mouvement vers feu
                {
                    int id_lieu = bdd.rpg11(k);//recuperer id lieu correspondant à la destination de ce camion
                    int posXlieu = bdd.rpg4(id_lieu);
                    int posYlieu = bdd.rpg44(id_lieu);
                    listCamion[k-1].moveCamion(k,posXlieu,posYlieu,bdd);//deplacement des camions sur incendie
                    bdd.bazooka("UPDATE camions SET etat = 3 WHERE id ="+k);
                }
                else if (bdd.rpg10(k)==3)//en intervention
                {

                    System.out.println("camion" +k+" citerne "+listCamion[k-1].getCiterne());
                    int id_lieu = bdd.rpg11(k);//recuperer id lieu correspondant à la destination de ce camion
                    int posXlieu = bdd.rpg4(id_lieu);
                    int posYlieu = bdd.rpg44(id_lieu);
                    if (listCamion[k-1].getCiterne() > 0)
                    {


                        for (int j=0; j<cpt; j++)
                        {
                            if (listFire[j].getPosx() == posXlieu && listFire[j].getPosy() == posYlieu)
                            {
                                boolean fin_feu = listFire[j].decreaseFire(bdd);

                                bdd.bazooka("UPDATE camions SET citerne = "+((listCamion[k-1].getCiterne())-1) +" where id = "+k);
                                listCamion[k-1].setCiterne((listCamion[k-1].getCiterne())-1);

                                if(bdd.rpg13(k)==0) //camion vide
                                {
                                    bdd.bazooka("UPDATE camions SET etat = 4 WHERE id ="+k);
                                    bdd.bazooka("UPDATE interventions set etat = 1 where lieu = "+bdd.rpg(posXlieu,posYlieu));

                                }
                                else if (bdd.rpg13(k)!=0 && fin_feu) //camion pas vide
                                {
                                    bdd.bazooka("UPDATE camions SET etat = 0 WHERE id ="+k);
                                    bdd.bazooka("UPDATE interventions set etat = 1 where lieu = "+bdd.rpg(posXlieu,posYlieu));
                                }
                            }

                        }

                    }
                    else
                    {
                        bdd.bazooka("UPDATE camions SET etat = 4 WHERE id ="+k);
                        bdd.bazooka("UPDATE interventions set etat = 1 where camion ="+k);
                    }


                }
                else if (bdd.rpg10(k)==4)//en mouvement vers réaprovisionnement
                {
                    listCamion[k-1].moveCamion(k,posxCaserne,posyCaserne,bdd);//deplacement des camions sur incendie
                    bdd.bazooka("UPDATE camions SET etat = 5 WHERE id ="+k);
                }
                else if (bdd.rpg10(k)==5)//en réaprovisionnement
                {
                    listCamion[k-1].setCiterne(3);
                    bdd.bazooka("UPDATE camions SET citerne = 3 WHERE id ="+k);
                    bdd.bazooka("UPDATE camions SET etat = 0 WHERE id ="+k);
                }
            }

            System.out.println(grid.toString());

            System.out.println("Fin Tour " + i);
            System.out.println("Nb de feu : " + cpt);

            System.out.println("#######################################");

        sleep(4000);
        }
    }
}