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

        FieldToCommentAddDbView(
                "/org/fx/tool/view/base/generate/field/field_comment_add_db/FieldToCommentAddDbView.fxml"),

        BeanToDisuniteModelFieldView(
                "/org/fx/tool/view/base/generate/field/bean_to_model/disunite/BeanToDisuniteModelFieldView.fxml"),

        BeanToUniteModelFieldView(
                "/org/fx/tool/view/base/generate/field/bean_to_model/unite/BeanToUniteModelFieldView.fxml"),

        ModelToModeAccessorView(
                "/org/fx/tool/view/base/generate/accessor/model_to_accessor/ModelToModeAccessorView.fxml"),

        DefineToFieldView("/org/fx/tool/view/base/generate/field/define_field/DefineToFieldView.fxml"),

        DefineToViewFieldView("/org/fx/tool/view/base/generate/field/define_view_field/DefineToViewFieldView.fxml"),

        DefineToFieldWithDbView("/org/fx/tool/view/base/generate/field/db_to_field/DefineToFieldWithDbView.fxml"),

        GenerateCharacterView("/org/fx/tool/view/base/generate/character/GenerateCharacterView.fxml"),
        
        DefineToMethodView("/org/fx/tool/view/base/generate/set_method/define_to_method/DefineToMethodView.fxml"),

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

    @FXML
    private void ui5(ActionEvent event) {
        loadUi(Path.BeanToDisuniteModelFieldView);
    }

    @FXML
    private void ui6(ActionEvent event) {
        loadUi(Path.BeanToUniteModelFieldView);
    }

    @FXML
    private void ui7(ActionEvent event) {
        loadUi(Path.ModelToModeAccessorView);
    }

    @FXML
    private void ui8(ActionEvent event) {
        loadUi(Path.DefineToFieldView);
    }

    @FXML
    private void ui9(ActionEvent event) {
        loadUi(Path.DefineToViewFieldView);
    }

    @FXML
    private void ui10(ActionEvent event) {
        loadUi(Path.DefineToFieldWithDbView);
    }

    @FXML
    private void ui11(ActionEvent event) {
        loadUi(Path.GenerateCharacterView);
    }

    @FXML
    private void ui12(ActionEvent event) {
        loadUi(Path.DefineToMethodView);
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
