package org.fx.tool.view.base.generate.field.define_view_field;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import org.apache.commons.lang3.StringUtils;
import org.fx.tool.view.base.generate.InputInfo;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;

public class DefineToViewFieldController implements Initializable {

    @FXML
    private SplitPane labelSplitPane;

    @FXML
    private SplitPane textAreaSplitPane;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextArea outputTextArea;

    private ChangeListener<String> changeLister = (observable, oldValue, newValue) -> {
        change(newValue);
    };

    private Function<InputInfo, String> converter;

    private Function<InputInfo, String> commentDivideFiled = inputInfo -> {
        String comment = String.join("", "/**", " ", inputInfo.getComment(), " */");
        String annotation = "@FXML";
        String field = String.join(" ", "private", "StringField", inputInfo.getField(), ";");

        return String.join("\n", comment, annotation, field).toString();
    };

    /**
     * @param lisner
     */
    private void initTextAreaAction(ChangeListener<String> lisner) {
        inputTextArea.textProperty().addListener(lisner);
    }

    /**
     * @param newValue
     */
    private void change(String newValue) {
        List<String> lines = Arrays.asList(newValue.split("\n"));

        if (StringUtils.isBlank(newValue)) {
            outputTextArea.setText("");
            return;
        }

        String fields = lines.stream()
                .map(String::trim)
                .filter(StringUtils::isNotBlank)
                .map(input -> Arrays.asList(input.split("\\|")))
                .filter(list -> list.size() == 3)
                .map(list -> {
                    String comment = list.get(1);
                    String field = list.get(2);
                    return InputInfo.InputInfoBuilder
                            .get()
                            .setComment(comment)
                            .setField(field)
                            .build();
                }).map(this.converter)
                .reduce("", (one, two) -> String.join("\n\n", one, two).toString());
        outputTextArea.setText(fields);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelSplitPane.getDividers().get(0).positionProperty().bindBidirectional(
                textAreaSplitPane.getDividers().get(0).positionProperty());
        initTextAreaAction(changeLister);
        initPromptText();
        converter = commentDivideFiled;

    }

    /**
     *
     */
    private void initPromptText() {
        inputTextArea.setPromptText(
                "例\r" +
                        "|ユーザID|userId|\r" +
                        "|オブジェクト|object|\r");
        outputTextArea.setPromptText(
                "例\r" +
                        "/** ユーザID　*/\r" +
                        "@FXML\r" +
                        "private StringField userId;\r" +
                        "\r" +
                        "/** オブジェクト　*/\r" +
                        "@FXML\r" +
                        "private StringField object;\r");
    }
}
