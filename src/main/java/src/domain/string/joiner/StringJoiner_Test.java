package src.domain.string.joiner;

import java.util.StringJoiner;

public class StringJoiner_Test {

    public static void main(String[] args) {
        StringJoiner joiner = new StringJoiner("(", "", ")");
        joiner.add("製品棚卸入力");
        joiner.add("滋賀工場");
        System.out.println(joiner.toString());
    }
}
