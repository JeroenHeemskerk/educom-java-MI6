package nu.educom.MI6.Backup;

import java.sql.ResultSet;
import java.util.List;

public class Main {


   public static void main(String[] args) {
    View view = new View();
    Crud crud = new Crud();
    Presenter presenter = new Presenter(view, crud);
    presenter.displayLogin();

  }
}