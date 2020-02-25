package src.domain.optional;

import java.math.BigDecimal;
import java.util.Optional;

public class Empty {
    public static void main(String... args) {
       Optional<BigDecimal> a= Optional.<BigDecimal>empty();
    }
}
