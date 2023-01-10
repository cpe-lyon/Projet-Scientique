import org.postgresql.util.PSQLException;

import java.sql.*;

public class BDD {

    private String url;
    private String user;
    private String pwd;

    public BDD (String url, String user, String pwd)
    {
        this.url = url;
        this.user = user;
        this.pwd = pwd;
    }

    public int rpg(int posX, int posY) //Recuperer id des feux
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pwd);
            if (conn != null)
            {
                String roquette = "SELECT id from LIEUX WHERE adresse_x = "+posX+" and adresse_y = "+posY+"";
                System.out.println("Query launched => " + roquette);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(roquette);
                while ( rs.next() )
                {
                    //System.out.println("oooooo"+rs.getInt("id")+"oooooooo");
                    return (rs.getInt("id"));


                }
                conn.close();


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
                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public float rpg2(int posX, int posY) //Recuperer id des feux
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pwd);
            if (conn != null)
            {
                String roquette = "SELECT intensite from LIEUX WHERE adresse_x = "+posX+" and adresse_y = "+posY+"";
                System.out.println("Query launched => " + roquette);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(roquette);
                while ( rs.next() )
                {
                    //System.out.println("oooooo"+rs.getFloat("intensite")+"oooooooo");
                    return (rs.getFloat("intensite"));


                }
                conn.close();


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
                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public float rpg3(int id) //Recuperer intensite des feux avec id
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pwd);
            if (conn != null)
            {
                String roquette = "SELECT intensite from LIEUX WHERE id ="+id;
                System.out.println("Query launched => " + roquette);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(roquette);
                while ( rs.next() )
                {
                    //System.out.println("oooooo"+rs.getFloat("intensite")+"oooooooo");
                    return (rs.getFloat("intensite"));


                }
                conn.close();


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
                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public int rpg4(int id) //Recuperer posx des feux avec id
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pwd);
            if (conn != null)
            {
                String roquette = "SELECT adresse_x from LIEUX WHERE id ="+id;
                System.out.println("Query launched => " + roquette);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(roquette);
                while ( rs.next() )
                {
                    //System.out.println("oooooo"+rs.getFloat("intensite")+"oooooooo");
                    return (rs.getInt("adresse_x"));


                }
                conn.close();


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
                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public int rpg44(int id) //Recuperer posy des feux avec id
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pwd);
            if (conn != null)
            {
                String roquette = "SELECT adresse_y from LIEUX WHERE id ="+id;
                System.out.println("Query launched => " + roquette);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(roquette);
                int cpt = 2;
                while ( rs.next() )
                {
                    if(cpt == 2)
                    {
                        //System.out.println("oooooo"+rs.getFloat("intensite")+"oooooooo");
                        return (rs.getInt("adresse_y"));
                    }
                    cpt++;

                }
                conn.close();


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
                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public boolean rpg6(int id) //verifie si le lieu est déja dans l'intervention
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pwd);
            if (conn != null)
            {
                String roquette = "SELECT EXISTS (SELECT lieu FROM interventions WHERE lieu="+id+")";

                System.out.println("Query launched => " + roquette);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(roquette);
                while ( rs.next() )
                {
                    //System.out.println("oooooo"+rs.getFloat("intensite")+"oooooooo");
                    return (true);

                }
                conn.close();


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
                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public int rpg5(int id) //Recuperer id lieu quand
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pwd);
            if (conn != null)
            {
                String roquette = "SELECT lieu from intervention WHERE lieu ="+id+"and etat =1";
                System.out.println("Query launched => " + roquette);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(roquette);
                while ( rs.next() )
                {

                    //System.out.println("oooooo"+rs.getFloat("intensite")+"oooooooo");
                        return (rs.getInt("lieu"));


                }
                conn.close();


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
                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public int rpg7() //retourn id camion dispo
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pwd);
            if (conn != null)
            {
                String roquette = "SELECT id FROM camions where etat = 0";

                System.out.println("Query launched => " + roquette);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(roquette);
                while ( rs.next() )
                {
                    //System.out.println("oooooo"+rs.getFloat("intensite")+"oooooooo");
                    return (rs.getInt("id"));

                }
                conn.close();


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
                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public int rpg8(int id) //retourn posx camion
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pwd);
            if (conn != null)
            {
                String roquette = "SELECT position_x FROM camions where id="+id;

                System.out.println("Query launched => " + roquette);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(roquette);
                while ( rs.next() )
                {
                    //System.out.println("oooooo"+rs.getFloat("intensite")+"oooooooo");
                    return (rs.getInt("position_x"));

                }
                conn.close();


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
                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public int rpg9(int id) //retourn posy camion
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pwd);
            if (conn != null)
            {
                String roquette = "SELECT position_y FROM camions where id="+id;

                System.out.println("Query launched => " + roquette);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(roquette);
                int cpt = 0;
                while ( rs.next() )
                {
                    //System.out.println("oooooo"+rs.getFloat("intensite")+"oooooooo");
                    return (rs.getInt("position_y"));

                }
                conn.close();


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
                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public int rpg10(int id) //retourn etat camion
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pwd);
            if (conn != null)
            {
                String roquette = "SELECT etat FROM camions where id="+id;

                System.out.println("Query launched => " + roquette);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(roquette);
                while ( rs.next() )
                {
                    //System.out.println("oooooo"+rs.getFloat("intensite")+"oooooooo");
                    return (rs.getInt("etat"));

                }
                conn.close();


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
                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public int rpg11(int id_camion) //retourn etat camion
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pwd);
            if (conn != null)
            {
                String roquette = "SELECT lieu FROM interventions where camion="+id_camion+" and etat = 0";

                System.out.println("Query launched => " + roquette);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(roquette);
                while ( rs.next() )
                {
                    //System.out.println("oooooo"+rs.getFloat("intensite")+"oooooooo");
                    return (rs.getInt("lieu"));

                }
                conn.close();


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
                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public int rpg12(int x, int y) //retourn id camion
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pwd);
            if (conn != null)
            {
                String roquette = "SELECT id FROM camions where position_x="+x+" and position_y = "+y;

                System.out.println("#### Query launched => " + roquette);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(roquette);
                while ( rs.next() )
                {
                    //System.out.println("oooooo"+rs.getFloat("intensite")+"oooooooo");
                    return (rs.getInt("id"));

                }
                conn.close();


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
                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public int rpg13(int id) //retourn etat camion
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pwd);
            if (conn != null)
            {
                String roquette = "SELECT citerne FROM camions where id="+id;

                System.out.println("Query launched => " + roquette);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(roquette);
                while ( rs.next() )
                {
                    //System.out.println("oooooo"+rs.getFloat("intensite")+"oooooooo");
                    return (rs.getInt("citerne"));

                }
                conn.close();


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
                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public int rpg14(int id) //retourn etat camion
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pwd);
            if (conn != null)
            {
                String roquette = "SELECT id FROM interventions where lieu ="+id+" and etat = 0";

                System.out.println("Query launched => " + roquette);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(roquette);
                while ( rs.next() )
                {
                    //System.out.println("oooooo"+rs.getFloat("intensite")+"oooooooo");
                    return (rs.getInt("id"));

                }
                conn.close();


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
                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public int rpg15(int id) //retourn eid camion a partir de id intervention
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pwd);
            if (conn != null)
            {
                String roquette = "SELECT camion FROM interventions where id ="+id;

                System.out.println("Query launched => " + roquette);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(roquette);
                while ( rs.next() )
                {
                    //System.out.println("oooooo"+rs.getFloat("intensite")+"oooooooo");
                    return (rs.getInt("camion"));

                }
                conn.close();


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
                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public void bazooka(String roquette) //modifier ou lire la bdd
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.user, this.pwd);
            if (conn != null)
            {
                System.out.println("Query launched => " + roquette);
                Statement stmt = conn.createStatement();
                //System.out.println("xx "+roquette);
                try{
                    //System.out.println("aa "+roquette);
                    stmt.executeUpdate(roquette);
                    //System.out.println("aa "+roquette);
                }
                catch (PSQLException ex)
                {
                    //System.out.println("bb "+roquette);
                    ex.printStackTrace();
                }
                conn.close();
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        } finally
        {
            try
            {
                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
