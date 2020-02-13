package src.domain.number;

import java.math.BigDecimal;

public class Compare {
    public static void main(String... args) {
        
        
        //-1小さい
        //0同じ
        //1大きい
       int result= BigDecimal.TEN.compareTo(BigDecimal.ONE);
        System.out.println(result);
    }

}
