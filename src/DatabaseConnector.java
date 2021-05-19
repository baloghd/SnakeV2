import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    private Connection connection = null;
    public DatabaseConnector() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:games.db");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public DatabaseConnector(boolean create_db_from_scratch) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:games.db");
            if (create_db_from_scratch) {
                Statement statement = connection.createStatement();
                statement.setQueryTimeout(2);
                statement.executeUpdate(
                        "DROP TABLE IF EXISTS game"
                );
                statement.executeUpdate(
                        "CREATE TABLE game (id INTEGER PRIMARY KEY AUTOINCREMENT, name STRING, score INTEGER)"
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean saveHighscore(String name, int score) {
        boolean saving_ok = true;
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate(
                    String.format(
                            "INSERT INTO game (name, score) VALUES ('%s', %s)", name, score
                    )

            );
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            saving_ok = false;
        }
        return saving_ok;
    }

    public String getBest10() {
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(2);
            ResultSet rs;
            rs = statement.executeQuery("SELECT * FROM game ORDER BY score DESC LIMIT 10");

            StringBuilder hs = new StringBuilder();
            hs.append("<html>");
            int c = 1;


            while (rs.next()) {
                hs.append(String.format("%d. ", c));
                hs.append(rs.getString("name"));
                hs.append(" (");
                hs.append(rs.getString("score"));
                hs.append(")<br/>");
                c++;
            }
            hs.append("</html>");
            return hs.toString();
            //return rs;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }


    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /*statement.executeUpdate("drop table if exists game");
            statement.executeUpdate("create table game (id integer, name string, score integer)");
            statement.executeUpdate("insert into game values(1, 'leo', 14)");
            statement.executeUpdate("insert into game values(2, 'yui', 22)");
    ResultSet rs = statement.executeQuery("select * from game");
            while (rs.next()) {
        // read the result set
        System.out.println("name = " + rs.getString("name"));
        System.out.println("id = " + rs.getInt("score"));
    }*/

}