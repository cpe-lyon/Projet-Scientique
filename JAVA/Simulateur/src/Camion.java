public class Camion
{
    //caserne de départ ou feu de départ si citerne pas vide
    private int posXStart;
    private int posYStart;
    //point intermédiaire parcours
    //private int posXMouv;
    //private int posYMouv;
    //point d'arrivée
    //private int posXEnd;
    //private int posYEnd;
    private int citerne; // 0 = vide, 1 = feu n1, 2 = feu n2, 3 = feu n3
    private int posXCaserne;
    private int posYCaserne;

    private int state; // 0 = dispo , 1= pas dispo, 2 = pas dispo (mvt) vers feu ,3 = pas dispo feu,  4 = pas dispo (mvt) vers reapro , 5 = repos ou reappro, 5 = fin feu

    public Camion(int posXStart, int posYStart, int posXCaserne, int posYCaserne)
    {
        this.posXStart = this.posXStart;
        this.posYStart = this.posYStart;
        this.posXCaserne = this.posXCaserne;
        this.posYCaserne = this.posYCaserne;
        this.state = 0;
        this.citerne = 3;
    }

    public int getPosXStart() {
        return posXStart;
    }

    public void setPosXStart(int posXStart) {
        this.posXStart = posXStart;
    }

    public int getPosYStart() {
        return posYStart;
    }

    public void setPosYStart(int posYStart) {
        this.posYStart = posYStart;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    /**
    public int getPosXMouv() {
        return posXMouv;
    }

    public void setPosXMouv(int posXMouv) {
        this.posXMouv = posXMouv;
    }

    public int getPosYMouv() {
        return posYMouv;
    }

    public void setPosYMouv(int posYMouv) {
        this.posYMouv = posYMouv;
    }

    public int getPosXEnd() {
        return posXEnd;
    }

    public void setPosXEnd(int posXEnd) {
        this.posXEnd = posXEnd;
    }

    public int getPosYEnd() {
        return posYEnd;
    }

    public void setPosYEnd(int posYEnd) {
        this.posYEnd = posYEnd;
    }
     **/

    public int getCiterne() {
        return citerne;
    }

    public void setCiterne(int citerne) {
        this.citerne = citerne;
    }

    public int getPosXCaserne() {
        return posXCaserne;
    }

    public void setPosXCaserne(int posXCaserne) {
        this.posXCaserne = posXCaserne;
    }

    public int getPosYCaserne() {
        return posYCaserne;
    }

    public void setPosYCaserne(int posYCaserne) {
        this.posYCaserne = posYCaserne;
    }

    public void moveCamion(int id_cam,int posXDest, int posYDest, BDD bdd)
    {
        /**
        int availableMvt = 4;

        boolean xAxe = false;
        boolean yAxe = false;

        this.setPosXEnd(posXDest);
        this.setPosYEnd(posYDest);

        for (int i = 0; i < availableMvt; i++)
        {

            if (xAxe == false)
            {
                if (this.posXStart < posXDest)
                {
                    this.posXMouv += 1;
                    posXStart = posXMouv;
                }
                else if (this.posXStart > posXDest)
                {
                    this.posXMouv -= 1;
                    posXStart = posXMouv;
                }
                else
                {
                    xAxe = true;
                }
            }
            if (yAxe == false && xAxe == true)
            {
                if (this.posYStart < posYDest)
                {
                    this.posYMouv += 1;
                    posYStart = posYMouv;
                }
                else if (this.posYStart > posYDest)
                {
                    this.posYMouv -= 1;
                    posYStart = posYMouv;
                }
                else
                {
                    yAxe = true;
                }
            }
        }**/
        this.setPosXStart(posXDest);
        this.setPosYStart(posYDest);

        bdd.bazooka("UPDATE camions SET position_x = "+posXDest+" WHERE id = "+id_cam);
        bdd.bazooka("UPDATE camions SET position_y = "+posYDest+" WHERE id = "+id_cam);


    }



}

