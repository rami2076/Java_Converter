package org.fx.tool.view.base.generate.field.field_field;

import com.google.common.base.CaseFormat;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import org.fx.tool.view.base.generate.field.InputField;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;

public class AddDbFieldController implements Initializable {

    @FXML
    private SplitPane labelSplitPane;

    @FXML
    private SplitPane textAreaSplitPane;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private TextField tableNameTextField;

    private ChangeListener<String> changeLister = (observable, oldValue, newValue) -> {
        change(newValue);
    };

    private Function<InputField, String> converter;

    private Function<InputField, String> commentDivideFiled = inputField -> {

        String annotation = format(inputField.getElement());

        return String.join("\n", inputField.getComment(), annotation, inputField.getField()).toString();
    };

    private String format(String element) {
        String tableName = Optional.ofNullable(this.tableNameTextField.getText()).orElse("");
        String columnName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, element);
        return String.format("@DB(\"%s.%s\")", tableName, columnName);
    }

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
        List<String> lines = Arrays.asList(newValue.split(";"));

        if (StringUtils.isBlank(newValue)) {
            outputTextArea.setText("");
            return;
        }

        String fields = lines.stream()
                .map(String::trim)
                .filter(StringUtils::isNotBlank)
                .map(input -> Arrays.asList(input.split("\n")))
                .filter(list -> list.size() == 2)
                .map(list -> {
                    String comment = list.get(0);
                    String field = list.get(1);
                    return InputField.InputFieldBuilder
                            .get()
                            .setComment(comment)
                            .setField(field)
                            .build();
                })
                .filter(InputField.isNotBlankField)
                .filter(InputField.isCorrectField)
                .map(InputField::parseElement)
                .map(this.converter)
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

        tableNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                    change(this.inputTextArea.getText());
                }
        );

    }

    /**
     *
     */
    private void initPromptText() {
        inputTextArea.setPromptText(
                "例\r" +
                        "/** ユーザID　*/\r" +
                        "private String userId;\r" +
                        "\r" +
                        "/** オブジェクト　*/\r" +
                        "private String object;\r"
        );
        outputTextArea.setPromptText(
                "例\r" +
                        "/** ユーザID　*/\r" +
                        "@DB(TableName.USER_ID)\r" +
                        "private String userId;\r" +
                        "\r" +
                        "/** オブジェクト　*/\r" +
                        "@DB(TableName.OBJECT)\r" +
                        "private String object;\r"
        );
    }


}
