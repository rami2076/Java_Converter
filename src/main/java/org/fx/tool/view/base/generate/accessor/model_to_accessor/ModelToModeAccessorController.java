package org.fx.tool.view.base.generate.accessor.model_to_accessor;

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
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ModelToModeAccessorController implements Initializable {

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


        //頭文字を大文字に変更
        String headUpperElement = headUpper(inputField.getElement());

        //signature
        String signature = "";
        if (inputField.getSignature().equals("StringProperty")) {
            signature = "String";
        } else {

            Pattern pattern = Pattern.compile("(?<=<).*(?=>)");
            Matcher matcher = pattern.matcher(inputField.getSignature());
            matcher.find();
            String genericsClass = matcher.group();
            signature = genericsClass;
        }


        String getter = getterFormat(signature, inputField.getElement(), headUpperElement);
        String setter = setterFormat(signature, inputField.getElement(), headUpperElement);

        return String.join("\n\n", getter, setter).toString();
    };

    private static String headUpper(String element) {
        String capitalizedName = Character.toTitleCase(element.charAt(0)) + element.substring(1);
        return capitalizedName;
    }

    private static String getterFormat(String signature, String field, String headUpperField) {

        StringJoiner joiner = new StringJoiner("\n");
        String line1 = String.format("public %s get%s(){", signature, headUpperField);
        String line2 = String.format("return %s.get();", field);
        String line3 = "}";

        joiner.add(line1).add(line2).add(line3);

        return joiner.toString();
    }

    private static String setterFormat(String signature, String field, String headUpperField) {

        StringJoiner joiner = new StringJoiner("\n");
        String line1 = String.format("public void set%s(%s %s){", headUpperField, signature, field);
        String line2 = String.format("this.%s.set(%s);", field, field);
        String line3 = "}";

        joiner.add(line1).add(line2).add(line3);
        return joiner.toString();
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
                        "@BindingProperty(controller=\"userShikibetsuShi\",bean=\"userId\")\r" +
                        "private StringProperty userId;\r" +
                        "\r" +
                        "/** オブジェクト　*/\r" +
                        "@BindingProperty\r" +
                        "private ObjectProperty<Object> object;\r"
        );
        outputTextArea.setPromptText(


                "例\r" +
                        "public String getUserId() {\r" +
                        "return userId.get();\r" +
                        "}\r" +
                        "\r" +
                        "public void setUserId(String userId){\r" +
                        "this.userId.set(userId);" +
                        "}\r" +
                        "\r" +
                        "public Object getObject() {\r" +
                        "return object.get();\r" +
                        "}\r" +
                        "\r" +
                        "public void setObject(Object object){\r" +
                        "this.object.set(object);\n" +
                        "}\r"
        );
    }


}
