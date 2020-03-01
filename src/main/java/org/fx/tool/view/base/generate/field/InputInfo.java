package org.fx.tool.view.base.generate.field;

public class InputInfo {
    private String comment;
    private String field;

    public static class InputInfoBuilder {
        private String comment;
        private String field;


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

        public InputInfo build() {
            return new InputInfo(this.comment, this.field);
        }
    }


    private InputInfo(String comment, String field) {
        this.comment = comment;
        this.field = field;
    }

    public String getComment() {
        return comment;
    }

    public String getField() {
        return field;
    }
}
