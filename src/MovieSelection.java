import processing.core.PApplet;
import processing.data.StringList;
import processing.core.PFont;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by Dylan on 15/03/2017.
 */

public class MovieSelection extends Main
{
    PApplet parent;
    static String driver = "org.sqlite.JDBC";
    String url = "jdbc:sqlite:Movies.sqlite";
    StringList info;


    MovieSelection(PApplet p)
    {
        parent = p;
        info = new StringList();
    }

    static
    {
        try
        {
            Class.forName(driver);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Movie> Movies = new ArrayList<>();

    /*public void printTunes(ArrayList<Movie> movies)
    {
        for(Movie t:movies)
        {
            System.out.println(t);
        }
    }*/

    void loadMovies(String ReturnMovie, PFont MovieInfo)
    {

        ResultSet rs;
        Movies.clear();
        try(Connection c = DriverManager.getConnection(url);
            PreparedStatement ps = c.prepareStatement("select * from MovieIndex where title like ?"))
        {
            ps.setString(1, ReturnMovie);

            rs = ps.executeQuery();

            int gap =0;
            String[] arr = new String[5];
            parent.textFont(MovieInfo);
            parent.fill(255);
            parent.textAlign(CENTER);
            parent.text("           Rating :" + "     /10", parent.width/2 - 100, parent.height / 10 + 150 );
            while (rs.next())
            {
                for(int i = 1; i< 5 ; i++) {
                    arr[i] = rs.getString(i);
                    parent.text(arr[i] , parent.width/2, parent.height / 10 + gap );
                    gap +=50;
                }

                Movie movie = new Movie(rs);
                Movies.add(movie);
            }
            //printTunes(Movies);
        }
        catch(SQLException e)
        {
            System.out.println("SQL Exception");
            e.printStackTrace();
        }
    }
}
