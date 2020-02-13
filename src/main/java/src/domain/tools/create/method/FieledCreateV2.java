package src.domain.tools.create.method;

import java.util.Scanner;

import com.google.common.base.CaseFormat;

public class FieledCreateV2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //FACTORY_CODE                                      工場コード
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

            convertAction(array[1], array[0]);

        }

        sc.close();
    }

    private static void convertAction(String comment, String element) {
     
      element=  CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, element);
        
        comment = String.join("", "/**", " ", comment , " */");
        String field = String.join(" ", "private", "String", element,";");
  
        System.out.println(comment);
        System.out.println(field);
    }

}
