package org.fx.tool.view.base.generate;

public class InputInfo {
    private String comment;
    private String field;
    private String signature;

    public String getSignature() {
        return signature;
    }

    public static class InputInfoBuilder {
        private String comment;
        private String field;
        private String signature;

        public static InputInfoBuilder get() {
            return new InputInfoBuilder();
        }

        public InputInfoBuilder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public InputInfoBuilder setField(String field) {
            this.field = field;
            return this;
        }

        public InputInfoBuilder setSignature(String signature) {

            switch (signature) {
            case "VARCHAR2":
                signature = "String";
                break;
            case "DATE":
                signature = "Hizuke";
                break;
            case "NUMBER":
                signature = "BigDecimal";
                break;
            default:
                signature = "Object";
                break;

            }

            this.signature = signature;
            return this;
        }

        public InputInfo build() {
            return new InputInfo(this.comment, this.field, this.signature);
        }
    }

    private InputInfo(String comment, String field, String signature) {
        this.comment = comment;
        this.field = field;
        this.signature = signature;
    }

    public String getComment() {
        return comment;
    }

    public String getField() {
        return field;
    }
}
