package src.domain.optional;

import java.math.BigDecimal;
import java.util.Optional;

public class Optional_Map_Test {

    public static void main(String[] args) {

        //BigDecimal tsumiageNo = BigDecimal.ONE;
        BigDecimal tsumiageNo = null;

        String tsumiageNoString = Optional.ofNullable(tsumiageNo).map(BigDecimal::toString).orElseGet(() -> "");

        System.out.println(tsumiageNoString);
    }
}
