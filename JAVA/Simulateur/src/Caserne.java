public class Caserne
{
    private int posX;
    private int posY;
    Camion listeCamion[] = new Camion[5];

    public Caserne(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Camion[] getListeCamion() {
        return listeCamion;
    }

    public void setListeCamion(Camion[] listeCamion) {
        this.listeCamion = listeCamion;
    }

    public void putCaserne(float[][] grid)
    {
        int min = 0;
        int maxX = grid.length;
        int maxY = grid[0].length;
        int rdmPosX = 0;
        int rdmPosY = 0;
        do
        {
            rdmPosX = min + (int)(Math.random()*(maxX-min));
            rdmPosY = min + (int)(Math.random()*(maxY-min));
        }
        while (grid[rdmPosX][rdmPosY] !=0);

        this.setPosX(rdmPosX);
        this.setPosY(rdmPosY);
        grid[rdmPosX][rdmPosY] = 18; //pompier caserne
    }

    public void putCaserne(int posX, int posY,float[][] grid)
    {
        this.setPosX(posX);
        this.setPosY(posY);
        grid[posX][posY] = 18; //pomier caserne
    }
}
