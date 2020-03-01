package org.fx.tool.view.base.generate;

import org.apache.commons.lang3.StringUtils;

import java.util.function.Predicate;

public class InputField {
    private String comment;
    private String field;

    private String modifier;
    private String signature;
    private String element;

    public String getElement() {
        return element;
    }

    public String getModifier() {
        return modifier;
    }

    public String getSignature() {
        return signature;
    }

    public static class InputFieldBuilder {
        private String comment;
        private String field;


        public static InputFieldBuilder get() {
            return new InputFieldBuilder();
        }

        public InputFieldBuilder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public InputFieldBuilder setField(String field) {

            String normalizeField = field.trim();
            this.field = normalizeField;
            return this;
        }

        public InputField build() {
            return new InputField(this.comment, this.field);
        }
    }


    private InputField(String comment, String field) {
        this.comment = comment;
        this.field = field;
    }

    public String getComment() {
        return comment;
    }

    public String getField() {
        return field;
    }

    public static Predicate<InputField> isNotBlankField = inputField -> StringUtils.isNotBlank(inputField.field);

    public static Predicate<InputField> isCorrectField = inputField -> inputField.field.split("\\s+").length == 3;

    public InputField parseElement() {
        String[] splitField = this.field.split("\\s+");
        this.modifier = splitField[0];
        this.signature = splitField[1];
        this.element = splitField[2];
        this.field = this.field + ";";
        return this;
    }
}
