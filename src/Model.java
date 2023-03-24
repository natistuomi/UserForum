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

    public String getPosts(){
        String output = "";
        try {
            Statement stmt = conn.createStatement();
            String SQLQuery = "SELECT * FROM nt19posts";
            ResultSet result = stmt.executeQuery(SQLQuery);
            ResultSetMetaData metadata = result.getMetaData();
            while (result.next()) {
                output += result.getString("title") + " by " + result.getString("authorID") + "\n" + result.getString("content") + "\n";
                System.out.println(output);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    public void close() throws SQLException {
        conn.close();
    }
}
