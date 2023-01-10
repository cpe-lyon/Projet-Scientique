import java.util.List;

public class Grid
{
    private int x = 6;
    private int y = 10;
    private float[][] grid;
    public Grid(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public float[][] gridCreation(BDD bdd)
    {
        float[][] grille = new float[this.x][this.y];

        for (int i=0; i<this.x; i++)
        {
            for (int j=0; j<this.y; j++)
            {
                bdd.bazooka("UPDATE LIEUX SET intensite = '0.0' WHERE id = "+bdd.rpg(i,j)+"");
                grille[i][j] = bdd.rpg2(i,j);

            }
        }

        this.grid = grille;
        return grille;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float[][] getGrid() {
        return grid;
    }

    public void setGrid(float[][] grid) {
        this.grid = grid;
    }

    @Override
    public String toString() {

        String retour = "";
        for (int i=0; i<this.x; i++)
        {
            for (int j=0; j<this.y; j++)
            {
                retour = retour + this.grid[i][j] + " ";
            }
            retour = retour + "\n";
        }
        return retour;
    }
}
