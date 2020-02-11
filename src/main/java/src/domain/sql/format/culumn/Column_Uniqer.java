package src.domain.sql.format.culumn;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Column_Uniqer {

    public static String m = "FACTORY_CODE  PLACE_CODE  NAME1   PALET_NUMBER    DEL_FLG ORDER_NUMBER    BUSINESS_NUMBER PAGE_NO_OYA PAGE_NO_KO  TRADE_TYPE  QUANTITY    STOCKTAKING_NUMBER  INPUT_SEQ   SECTION_CODE    PRODUCT_KIND    IN_QTY  OUT_QTY REMAINDER_QTY   STOCK_QTY   FACTORY_CODE    PLACE_CODE  NAME1   PALET_NUMBER    DEL_FLG ORDER_NUMBER    BUSINESS_NUMBER PAGE_NO_OYA PAGE_NO_KO  TRADE_TYPE  QUANTITY    STOCKTAKING_NUMBER  INPUT_SEQ   SECTION_CODE    PRODUCT_KIND    WRITE_WRONG_TYPE    REMAINDER_QTY   STOCK_QTY   INSTALLMENT_NUMBER  INSTALLMENT_SM_NUMBER";

    public static void main(String[] args) {
        String[] array = m.split("\\s+");
        Set<String> set = new LinkedHashSet<>(Arrays.asList(array));
        set.stream().forEach(System.out::println);

    }
}
