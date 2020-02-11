package src.domain.tools.create.method;

import java.util.Scanner;

public class FieledCreate {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //工場コード                                factoryCode
        //上記フォーマットをコメント付きフィールドに変換する。
        while (true) {
            String line = sc.nextLine();
            String[] array = line.split("\\s+");

            if (array[0].equals("q")) {
                break;
            }

            if (array.length == 0 || array.length == 1) {
                continue;
            }

            convertAction(array[0], array[1]);

        }

        sc.close();
    }

    private static void convertAction(String comment, String element) {



        comment = String.join("", "/**", " ", comment , " */");
        String field = String.join(" ", "private", "String", element,";");

        System.out.println(comment);
        System.out.println(field);
    }

}
