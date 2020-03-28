package org.fx.tool.view.base.generate.character;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.CaseFormat;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;

public class GenerateCharacterController implements Initializable {

    //基礎項目
    @FXML
    private SplitPane labelSplitPane;

    @FXML
    private SplitPane textAreaSplitPane;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextArea outputTextArea;

    //改行数
    @FXML
    private TextField lineSeparatorCountTextField;

    //ケースボタン　入力
    @FXML
    private RadioButton inConstantRadio;
    @FXML
    private RadioButton inCamelRadio;
    @FXML
    private RadioButton inPascalRadio;
    @FXML
    private RadioButton inSnakeRadio;
    @FXML
    private RadioButton inSpaceRadio;

    //ケースボタン 出力
    @FXML
    private RadioButton outConstantRadio;
    @FXML
    private RadioButton outCamelRadio;
    @FXML
    private RadioButton outPascalRadio;
    @FXML
    private RadioButton outSnakeRadio;
    @FXML
    private RadioButton outSpaceRadio;

    //ラジオグループ
    @FXML
    private ToggleGroup inputCaseGroup;
    @FXML
    private ToggleGroup outputCaseGroup;

    private CaseFormat inputCaseFormat = CaseFormat.UPPER_UNDERSCORE;

    private CaseFormat outputCaseFormat = CaseFormat.UPPER_UNDERSCORE;

    private ChangeListener<String> changeLister = (observable, oldValue, newValue) -> {
        change(newValue);
    };

    private String lineSeparetor = "\n";

    private Function<String, String> converter;

    private Function<String, String> commentDivideFiled = line -> {

        return inputCaseFormat.to(outputCaseFormat, line);

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
                .map(line -> {

                    return line;
                }).map(this.converter)
                .reduce("",
                        (one, two) -> String.join(StringUtils.repeat(lineSeparetor,
                                Optional.<String> of(this.lineSeparatorCountTextField.getText())
                                        .map(s -> StringUtils.defaultIfBlank(s, "2"))
                                        .map(Integer::parseInt).orElse(2)),
                                one, two).toString());
        outputTextArea.setText(fields);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelSplitPane.getDividers().get(0).positionProperty().bindBidirectional(
                textAreaSplitPane.getDividers().get(0).positionProperty());
        initTextAreaAction(changeLister);
        initPromptText();
        converter = commentDivideFiled;

        lineSeparatorCountTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            String str = newValue;
            if (str == null) {
                return;
            }
            if (str.length() > 1) {
                lineSeparatorCountTextField.setText(oldValue);
            }

            change(this.inputTextArea.getText());
        });

        Pattern numberPattern = Pattern.compile("^[^1-5]{1}$");
        TextFormatter<String> lowerFormatter = new TextFormatter<>(change -> {
            String newStr = numberPattern.matcher(change.getText()).replaceAll("");

            int diffcount = change.getText().length() - newStr.length();
            change.setAnchor(change.getAnchor() - diffcount);
            change.setCaretPosition(change.getCaretPosition() - diffcount);
            change.setText(newStr);
            return change;
        });

        lineSeparatorCountTextField.setTextFormatter(lowerFormatter);

        inputCaseGroup.selectedToggleProperty().addListener((observable, oldProperty, newProperty) -> {
            RadioButton r = (RadioButton) newProperty;

            if (r.equals(inConstantRadio)) {
                inputCaseFormat = CaseFormat.UPPER_UNDERSCORE;
            } else if (r.equals(inCamelRadio)) {
                inputCaseFormat = CaseFormat.LOWER_CAMEL;
            } else if (r.equals(inPascalRadio)) {
                inputCaseFormat = CaseFormat.UPPER_CAMEL;
            } else if (r.equals(inSnakeRadio)) {
                inputCaseFormat = CaseFormat.LOWER_UNDERSCORE;
            } else if (r.equals(inSpaceRadio)) {
                //未実装
            }

            change(inputTextArea.getText());

        });

        outputCaseGroup.selectedToggleProperty().addListener((observable, oldProperty, newProperty) -> {
            RadioButton r = (RadioButton) newProperty;

            if (r.equals(outConstantRadio)) {
                outputCaseFormat = CaseFormat.UPPER_UNDERSCORE;
            } else if (r.equals(outCamelRadio)) {
                outputCaseFormat = CaseFormat.LOWER_CAMEL;
            } else if (r.equals(outPascalRadio)) {
                outputCaseFormat = CaseFormat.UPPER_CAMEL;
            } else if (r.equals(outSnakeRadio)) {
                outputCaseFormat = CaseFormat.LOWER_UNDERSCORE;
            } else if (r.equals(outSpaceRadio)) {
                //未実装
            }

            change(inputTextArea.getText());
        });

    }

    /**
     *
     */
    private void initPromptText() {
        inputTextArea.setPromptText(
                "");
        outputTextArea.setPromptText(
                "");
    }
}
