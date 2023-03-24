import java.sql.SQLException;

public class Controller {
    Model model;
    View view;
    DatabaseLogin a = new DatabaseLogin();

    public Controller(Model model, View view) throws SQLException {
        this.model = model;
        this.view = view;
        model.connect(a);
        String posts = model.getPosts();
        //Send posts to textarea in view?
        model.close();
    }

    public static void main(String[] args) throws SQLException {
        Model m = new Model();
        View v = new View();
        Controller c = new Controller(m,v);
    }
}
