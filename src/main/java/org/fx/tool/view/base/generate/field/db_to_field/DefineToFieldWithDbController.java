package org.fx.tool.view.base.generate.field.db_to_field;

import com.google.common.base.CaseFormat;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import org.fx.tool.view.base.generate.InputInfo;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;

public class DefineToFieldWithDbController implements Initializable {

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

    private Function<InputInfo, String> converter;

    private Function<InputInfo, String> commentDivideFiled = inputInfo -> {

        String element = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, inputInfo.getField());

        String comment = String.join("", "/**", " ", inputInfo.getComment(), " */");
        String annotation = format(element);
        String field = String.join(" ", "private", inputInfo.getSignature(), element, ";");

        return String.join("\n", comment, annotation, field).toString();
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
        List<String> lines = Arrays.asList(newValue.split("\n"));

        if (StringUtils.isBlank(newValue)) {
            outputTextArea.setText("");
            return;
        }

        String fields = lines.stream()
                .map(String::trim)
                .filter(StringUtils::isNotBlank)
                .map(input -> Arrays.asList(input.split("\\s+")))
                .filter(list -> list.size() == 3)
                .map(list -> {
                    String comment = list.get(0);
                    String field = list.get(1);
                    String signature = list.get(2);
                    return InputInfo.InputInfoBuilder
                            .get()
                            .setComment(comment)
                            .setField(field)
                            .setSignature(signature)
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

        tableNameTextField.textProperty()
                .addListener((observable, oldValue, newValue) -> change(this.inputTextArea.getText()));
    }

    /**
     *
     */
    private void initPromptText() {
        inputTextArea.setPromptText(
                "例\r" +
                        "ユーザID USER_ID                 VARCHAR2\r" +
                        "数値コード NUMBER_CODE    NUMBER\r" +
                        "登録日 INSERT_DATE   DATE\r");
        outputTextArea.setPromptText(
                "例\r" +
                        "/** ユーザID　*/\r" +
                        "private String userId;\r" +
                        "/** 数値コード　*/\r" +
                        "private BigDecimal numberCode;\r" +
                        "/** 登録日　*/\r" +
                        "\"private Hizuke insertDate;\\r");
    }
}
