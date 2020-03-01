package org.fx.tool.view.base.generate.field.bean_to_model.disunite;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import org.apache.commons.lang3.StringUtils;
import org.fx.tool.view.base.generate.InputField;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BeanToDisuniteModelFieldController implements Initializable {

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

    private Function<InputField, String> converter;

    private Function<InputField, String> commentDivideFiled = inputField -> {

        final String annotation = annotationFormat("", inputField.getElement());

        String signature = "";
        if (inputField.getSignature().equals("String")) {
            signature = "StringProperty";
        } else {
            signature = signatureFormat(inputField.getSignature());
        }

        String field = String.join(" ", inputField.getModifier(), signature, inputField.getElement() + ";");

        return String.join("\n", inputField.getComment(), annotation, field).toString();
    };

    private static String signatureFormat(String signature) {
        return String.format("ObjectProperty<%s>", signature);

    }

    private static String annotationFormat(String controllerField, String beanField) {

        controllerField = controllerField.replace(";", "");
        beanField = beanField.replace(";", "");

        //そのまま出力
        return String.format("@BindingProperty(controller=\"%s\",bean=\"%s\")", controllerField, beanField);
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
                .map(list -> list.stream()
                        .map(String::trim)
                        .filter(StringUtils::isNotBlank)
                        .filter(in -> !in.startsWith("@"))
                        .collect(Collectors.toList())
                )
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


    }

    /**
     *
     */
    private void initPromptText() {
        inputTextArea.setPromptText(
                "例\r" +
                        "/** ユーザID　*/\r" +
                        "@Annotation\n\r" +
                        "private String userId;\r" +
                        "\r" +
                        "/** オブジェクト　*/\r" +
                        "private Object object;\r"
        );
        outputTextArea.setPromptText(
                "例\r" +
                        "/** ユーザID　*/\r" +
                        "@BindingProperty(controller=\"\",bean=\"userId\")\r" +
                        "private StringProperty userId;\r" +
                        "\r" +
                        "/** オブジェクト　*/\r" +
                        "@BindingProperty(controller=\"\",bean=\"object\")\r" +
                        "private ObjectProperty<Object> object;\r"
        );
    }


}
