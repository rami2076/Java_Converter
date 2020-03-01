package src.domain.gui;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import src.Top;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;


public class Main implements Initializable {


    @FXML
    private SplitPane TextAreaDivinder;

    @FXML
    private SplitPane LabelDivinder;

    @FXML
    private TextArea Innput;

    @FXML
    private TextArea output;


    @FXML
    private Button aaj;

    @FXML
    private Button moveee;

    private int a = 0;


    private Stage primaryStage;

    private ChangeListener<String> ab = (observable, oldValue, newValue) -> {
        changebb(newValue);
    };

    private ChangeListener<String> ac = (observable, oldValue, newValue) -> {
        changeaa(newValue);
    };


    /**
     * @param newValue
     */
    private void changeaa(String newValue) {
        List<String> lines = Arrays.asList(newValue.split("\n"));
        int i = 100;
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            line = "" + i + line + "\n";
            sb.append(line);
            i++;
        }
        output.setText(sb.toString());

    }

    /**
     * @param newValue
     */
    private void changebb(String newValue) {
        List<String> lines = Arrays.asList(newValue.split("\n"));
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            line = "" + i + line + "\n";
            sb.append(line);
            i++;
        }
        output.setText(sb.toString());

    }


    /**
     * @param url
     * @param bundle
     */
    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle bundle) {
        LabelDivinder.getDividers().get(0).positionProperty().bindBidirectional(
                TextAreaDivinder.getDividers().get(0).positionProperty());

        initaa(ab);

    }

    @FXML
    private void goToSub(ActionEvent event) throws IOException {
        Parent tableViewParent = null;
        URL location = Top.class.getClass().getResource("/src/domain/gui/sub/Sub.fxml");
        tableViewParent = FXMLLoader.load(location);

        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    @FXML
    private void switchAction(ActionEvent event){
        if ((this.a % 2) == 0) {
            initaa(ab);
            changebb(Innput.getText());
        } else {
            initaa(ac);
            changeaa(Innput.getText());
        }
        this.a++;
    }

    /**
     * @param lisner
     */
    private void initaa(ChangeListener<String> lisner) {
        Innput.textProperty().addListener(lisner);
    }
}
