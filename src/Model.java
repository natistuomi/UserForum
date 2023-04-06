import DatabaseModel.BCrypt;

import java.sql.*;

public class Model {
    Connection conn = null;

    public Model() {
    }

    public void connect(DatabaseLogin a){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + a.getHost() + ":" + a.getPort() + "/" + a.getDatabase() + "? "+
                    "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", a.getUser(),a.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getAllPosts(){
        String output = "";
        try {
            Statement stmt = conn.createStatement();
            String SQLQuery = "SELECT * FROM nt19posts";
            ResultSet result = stmt.executeQuery(SQLQuery);
            ResultSetMetaData metadata = result.getMetaData();
            while (result.next()) {
                output += "\n" + result.getString("title") + " by " + result.getString("authorID") + "\n" + result.getString("content") + "\n";
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    public boolean login(String username, String password){
        String hashed = DatabaseModel.BCrypt.hashpw(password, DatabaseModel.BCrypt.gensalt());
        String p = "";
        try {
            Statement stmt = conn.createStatement();
            String SQLQuery = "SELECT * FROM nt19login WHERE username = " + username;
            ResultSet result = stmt.executeQuery(SQLQuery);
            ResultSetMetaData metadata = result.getMetaData();
            p = result.getString("password");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BCrypt.checkpw(p, hashed);
    }

    public void close() throws SQLException {
        conn.close();
    }
}
