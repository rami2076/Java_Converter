package src.domain.optional;

import java.util.Optional;

public class MultipleAction {

    public static void main(String args[]) {

        Optional<String> a = Optional.ofNullable(null);
        String c = a.map(b -> b + b).orElse("C");
        String d = a.map(b -> b + b + b).orElse("D");

        System.out.println(c);
        System.out.println(d);

    }
}
