package org.fx.tool.view.base;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Base implements Initializable {


    enum Path {
        CommentToFieldView("/org/fx/tool/view/base/generate/field/commnet_field/CommentToFieldView.fxml"),

        FieldToCommentView("/org/fx/tool/view/base/generate/field/field_comment/FieldToCommentView.fxml"),

        AddDbFieldView("/org/fx/tool/view/base/generate/field/field_field/AddDbFieldView.fxml"),

        FieldToCommentAddDbView("/org/fx/tool/view/base/generate/field/field_comment_add_db/FieldToCommentAddDbView.fxml"),

        Ui2("/org/fx/tool/view/base/ui2/Ui2.fxml"),

        Ui3("/org/fx/tool/view/base/ui3/Ui3.fxml");


        private String pathString;

        Path(String path) {
            this.pathString = path;
        }

        public String getPathString() {
            return this.pathString;
        }
    }


    @FXML
    private BorderPane borderPane;

    @FXML
    private void ui1(ActionEvent event) {
        loadUi(Path.CommentToFieldView);
    }

    @FXML
    private void ui2(ActionEvent event) {
        loadUi(Path.FieldToCommentView);

    }

    @FXML
    private void ui3(ActionEvent event) {

        loadUi(Path.AddDbFieldView);
    }

    @FXML
    private void ui4(ActionEvent event) {
        loadUi(Path.FieldToCommentAddDbView);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private Map<Path, Parent> map = new HashMap<>();

    private void loadUi(Path path) {

        if (map.containsKey(path)) {
            borderPane.setCenter(map.get(path));
            return;
        }


        Parent root = null;
        URL location = getClass().getResource(path.getPathString());


        try {
            root = FXMLLoader.load(location);
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put(path, root);
        borderPane.setCenter(root);

    }
}
