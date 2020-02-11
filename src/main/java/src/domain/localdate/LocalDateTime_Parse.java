package src.domain.localdate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author glxy1079
 *
 */
public class LocalDateTime_Parse {

    static String taishozuki = "2020/02/20";

    static DateTimeFormatter formatter_ = DateTimeFormatter.ofPattern("yyyy/MM");

    public static void main(String[] args) {
        System.out.println(getTaishozuki("yyyy/MM"));

        LocalDate localDate = LocalDate.parse(taishozuki, formatter_);

        //System.out.println(localDate.toString());
    }

    public static String getTaishozuki(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate localDate = LocalDate.parse(taishozuki, formatter_);
        return localDate.format(formatter);
    }

}
