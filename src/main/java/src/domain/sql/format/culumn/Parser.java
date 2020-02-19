package src.domain.sql.format.culumn;

import java.util.Scanner;

import com.google.common.base.CaseFormat;

public class Parser {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();

            if (line.equals("q")) {
                break;
            }
            line = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, line);
            System.out.println(line);
        }
        sc.close();
    }
}
