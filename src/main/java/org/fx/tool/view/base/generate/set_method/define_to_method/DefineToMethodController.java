package org.fx.tool.view.base.generate.set_method.define_to_method;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.fx.tool.view.base.generate.InputInfo;

import com.google.common.base.CaseFormat;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DefineToMethodController implements Initializable {

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

        String setMethod = format(inputInfo);

        return setMethod;
    };

    private String format(InputInfo inputInfo) {

        String upperField = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, inputInfo.getField());

        String formatPattern;
        switch (inputInfo.getSignature()) {
        case "String":
            formatPattern = "set%s(info.get%s());";
            break;
        case "Hizuke":
            formatPattern = "set%s(info.get%s());";
            break;
        case "BigDecimal":
            formatPattern = "set%s(  ObjectUtils.<BigDecimal>defaultIfNull( info.get%s(), BigDecimal.ZERO));";
            break;
        default:
            formatPattern = "set%s(info.get%s());";
            break;
        }

        return String.format(formatPattern, upperField, upperField);

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
                .reduce("", (one, two) -> String.join("\n", one, two).toString());
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
