package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Connector;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScoresController implements Initializable {
   @FXML
   private ListView scores;
    @FXML
    private Button backButton;

    public void setbackButAction(ActionEvent actionEvent) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/sample.fxml"));;
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connector connector = new Connector();
        scores.getItems().addAll(connector.getResultsFromDataBase());
    }
}

