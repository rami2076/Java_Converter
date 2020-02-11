package src.domain.word.format;

import java.util.Scanner;
import java.util.StringJoiner;

public class WordFormat {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Integer itemCount = Integer.parseInt(sc.next());

        for (Integer i = 0; i < itemCount / 2; i++) {
            StringJoiner joiner = new StringJoiner("|", "|", "|");
            String one = sc.next();
            String two = sc.next();
            System.out.println(  joiner.add(one).add(two).toString());
        }

    }

}
