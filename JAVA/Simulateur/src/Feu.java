import java.util.ArrayList;

public class Feu
{
    private double intensite;
    private int posx;
    private int posy;



    public Feu() {

    }

    public int increaseIntensite(float[][] grille, Feu[] rengoku, int cpt, BDD bdd)
    {
        int id;

        if (this.intensite >= 4 && this.intensite < 6)
        {
            this.intensite = this.intensite + 0.1;

            bdd.bazooka("UPDATE LIEUX SET intensite = '"+this.intensite+"' WHERE id = "+bdd.rpg(this.getPosx(),this.getPosy())+"");
        }
        else if (this.intensite >= 6 && this.intensite < 8)
        {
            this.intensite = this.intensite + 0.2;
            bdd.bazooka("UPDATE LIEUX SET intensite = '"+this.intensite+"' WHERE id = "+bdd.rpg(this.getPosx(),this.getPosy())+"");
        }
        else if (this.intensite >= 8 && this.intensite < 10)
        {
            this.intensite = this.intensite + 0.4;
            bdd.bazooka("UPDATE LIEUX SET intensite = '"+this.intensite+"' WHERE id = "+bdd.rpg(this.getPosx(),this.getPosy())+"");
        }
        else if (this.intensite >= 10 && this.intensite<12)
        {
            this.intensite = 11;
            bdd.bazooka("UPDATE LIEUX SET intensite = '"+this.intensite+"' WHERE id = "+bdd.rpg(this.getPosx(),this.getPosy())+"");

            boolean is_in_intervention = bdd.rpg6(bdd.rpg(this.getPosx(),this.getPosy()));
            if (is_in_intervention)
            {
                int id_int = bdd.rpg14(bdd.rpg(this.getPosx(),this.getPosy()));
                int id_camion = bdd.rpg15(id_int);
                bdd.bazooka("UPDATE interventions set etat = 1 where id = "+ id_int);
                bdd.bazooka("UPDATE camions set etat = 0 where id = "+ id_camion);
            }
            cpt = spreading(grille,rengoku, cpt,bdd);
        }

        grille[this.posx][this.posy] = (float) this.intensite;

        return cpt;

    }

    public int spreading(float[][] grille, Feu[] rengoku, int cpt, BDD bdd) //fire 9th form
    {
        if (this.intensite < 12)
        {
            this.intensite = 12;//brulé ne peut plus se propager
            bdd.bazooka("UPDATE LIEUX SET intensite = '"+this.intensite+"' WHERE id = "+bdd.rpg(this.getPosx(),this.getPosy())+"");
            int feuPosX = this.getPosx();
            int feuPosY = this.getPosy();

            int xSizeGrid = grille.length;
            int ySizeGrid = grille[0].length;

            int upper = 3;
            int lower = 1;

            if(feuPosX - 1 >= 0 && feuPosY - 1 >= 0) //haut gauche + vérifie si pas déjà feu
            {
                if(grille[feuPosX-1][feuPosY-1] == 0)
                {
                    if(((int) (Math.random() * (upper - lower)) + lower) == 2)
                    {
                        Feu feu = new Feu();
                        feu.putFire(feuPosX - 1, feuPosY - 1, 4, grille,bdd);
                        //bdd.bazooka("INSERT INTO lieux ('"+cpt+"','"+(feuPosX - 1)+"','"+(feuPosY - 1)+"','4')");
                        rengoku[cpt] = feu;
                        //rengoku[cpt].toString();
                        cpt = cpt + 1;
                    }
                }

            }
            if(feuPosX - 1 >= 0)
            {
                if(grille[feuPosX-1][feuPosY] == 0)
                {
                    if(((int) (Math.random() * (upper - lower)) + lower) == 2)
                    {
                        Feu feu = new Feu();
                        feu.putFire(feuPosX - 1, feuPosY, 4, grille,bdd);
                        //bdd.bazooka("INSERT INTO lieux ('"+cpt+"','"+(feuPosX - 1)+"','"+(feuPosY)+"','4')");
                        rengoku[cpt] = feu;
                        cpt = cpt + 1;
                    }
                }
            }



            if(feuPosX - 1 >= 0 && feuPosY + 1 < ySizeGrid) //haut droite
            {
                if(grille[feuPosX-1][feuPosY+1] == 0)
                {
                    if(((int) (Math.random() * (upper - lower)) + lower) == 2)
                    {
                        Feu feu = new Feu();
                        feu.putFire(feuPosX - 1, feuPosY + 1, 4, grille,bdd);
                        //bdd.bazooka("INSERT INTO lieux ('"+cpt+"','"+(feuPosX - 1)+"','"+(feuPosY + 1)+"','4')");
                        rengoku[cpt] = feu;
                        cpt = cpt + 1;
                    }
                }
            }
            if(feuPosY - 1 >= 0)  //gauche
            {
                if(grille[feuPosX][feuPosY-1] == 0)
                {
                    if(((int) (Math.random() * (upper - lower)) + lower) == 2)
                    {
                        Feu feu = new Feu();
                        feu.putFire(feuPosX, feuPosY - 1, 4, grille,bdd);
                        //bdd.bazooka("INSERT INTO lieux ('"+cpt+"','"+(feuPosX)+"','"+(feuPosY - 1)+"','4')");
                        rengoku[cpt] = feu;
                        cpt = cpt + 1;

                    }
                }
            }
            if(feuPosY + 1 < ySizeGrid)  //droite
            {
                if(grille[feuPosX][feuPosY+1] == 0)
                {
                    if (((int) (Math.random() * (upper - lower)) + lower) == 2) {
                        Feu feu = new Feu();
                        feu.putFire(feuPosX, feuPosY + 1, 4, grille,bdd);
                        //bdd.bazooka("INSERT INTO lieux ('"+cpt+"','"+(feuPosX)+"','"+(feuPosY + 1)+"','4')");
                        rengoku[cpt] = feu;
                        cpt = cpt + 1;

                    }
                }
            }
            if(feuPosX + 1 < xSizeGrid && feuPosY - 1 >= 0)  //bas gauche
            {
                if(grille[feuPosX+1][feuPosY-1] == 0)
                {
                    if(((int) (Math.random() * (upper - lower)) + lower) == 2)
                    {
                        Feu feu = new Feu();
                        feu.putFire(feuPosX + 1, feuPosY - 1, 4, grille,bdd);
                        //bdd.bazooka("INSERT INTO lieux ('"+cpt+"','"+(feuPosX + 1)+"','"+(feuPosY - 1)+"','4')");
                        rengoku[cpt] = feu;
                        cpt = cpt + 1;

                    }
                }
            }
            if(feuPosX + 1 < xSizeGrid)  //bas
            {
                if(grille[feuPosX+1][feuPosY] == 0)
                {
                    if(((int) (Math.random() * (upper - lower)) + lower) == 2)
                    {
                        Feu feu = new Feu();
                        feu.putFire(feuPosX + 1, feuPosY, 4, grille,bdd);
                        //bdd.bazooka("INSERT INTO lieux ('"+cpt+"','"+(feuPosX + 1)+"','"+(feuPosY)+"','4')");
                        rengoku[cpt] = feu;
                        cpt = cpt + 1;

                    }
                }
            }
            if(feuPosX + 1 < xSizeGrid && feuPosY + 1 < ySizeGrid)  //haut droite
            {
                if(grille[feuPosX+1][feuPosY+1] == 0)
                {
                    if(((int) (Math.random() * (upper - lower)) + lower) == 2)
                    {
                        Feu feu = new Feu();
                        feu.putFire(feuPosX + 1, feuPosY + 1, 4, grille,bdd);
                        //bdd.bazooka("INSERT INTO lieux ('"+cpt+"','"+(feuPosX + 1)+"','"+(feuPosY + 1)+"','4')");
                        rengoku[cpt] = feu;
                        cpt = cpt + 1;

                    }
                }
            }


        }
        return cpt;
    }
    public boolean decreaseFire (BDD bdd)
    {
        if (this.intensite > 4)
        {
            this.intensite -= 2;
            bdd.bazooka("UPDATE LIEUX SET intensite = '"+this.intensite+"' WHERE id = "+bdd.rpg(this.getPosx(),this.getPosy())+"");
            return false;
        }
        else
        {
            this.intensite = 0;
            bdd.bazooka("UPDATE LIEUX SET intensite = 0 WHERE id = "+bdd.rpg(this.getPosx(),this.getPosy())+"");
            return true;
        }
    }
    public double getIntensite() {
        return intensite;
    }

    public void setIntensite(double intensite) {
        this.intensite = intensite;
    }

    public int putFire(float[][] grid,int cpt, BDD bdd)
    {
        int min = 0;
        int min_int = 4;
        int maxX = grid.length;
        int maxY = grid[0].length;
        float maxIntensite = 7;
        int rdmPosX;
        int rdmPosY;
        do
        {
            rdmPosX = min + (int)(Math.random()*(maxX-min));
            rdmPosY = min + (int)(Math.random()*(maxY-min));
        }
        while (grid[rdmPosX][rdmPosY] !=0);
        this.setPosx(rdmPosX);
        this.setPosy(rdmPosY);
        float intensite = min_int + (float)(Math.random()*(maxIntensite-min_int));
        intensite = (float)(Math.round(intensite*10)/10.0);
        this.setIntensite(intensite);
        grid[rdmPosX][rdmPosY] = intensite;
        //bdd.bazooka("INSERT INTO lieux values ("+bdd.rpg(this.getPosx(),this.getPosy())+","+(this.getPosy())+","+(this.getPosx())+",4)");
        bdd.bazooka("UPDATE LIEUX SET intensite = '"+this.intensite+"' WHERE id = "+bdd.rpg(this.getPosx(),this.getPosy())+"");
        return cpt+1;
    }

    public void putFire(int posX, int posY, float intensite, float[][] grid, BDD bdd)
    {
        this.setPosx(posX);
        this.setPosy(posY);
        this.setIntensite(intensite);
        grid[posX][posY] = intensite;
        //bdd.bazooka("INSERT INTO lieux values ("+bdd.rpg(this.getPosx(),this.getPosy())+","+(this.getPosy())+","+(this.getPosx())+",4)");
        bdd.bazooka("UPDATE LIEUX SET intensite = '"+this.intensite+"' WHERE id = "+bdd.rpg(this.getPosx(),this.getPosy())+"");

    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public String toString()
    {
        return "Feu [posx=" + posx + ", posy=" + posy + ", intensite=" + intensite + "]";
    }


}
