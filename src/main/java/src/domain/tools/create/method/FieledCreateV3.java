package src.domain.tools.create.method;

import java.util.Scanner;

import com.google.common.base.CaseFormat;

public class FieledCreateV3 {

    public static String DB_NAME ;
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ///** 工場コード */
       // private String factoryCode ;
        //上記フォーマットに@DB("{{DB_NAME}}.FACTORY_CODE")とする。
        System.out.println("DB名を入力");
        DB_NAME = sc.nextLine();
        
        while (true) {
            String line = sc.nextLine();
            line = line.trim();
            String[] array = line.split("\\s+");

            if (array[0].equals("q")) {
                break;
            }

            if (array.length == 0 || array.length == 1) {
                continue;
            }
            

            if(line.startsWith("private")) {
                convertAction(line , array[2]);
            }else {
                System.out.println(line);
            }
            

        }

        sc.close();
    }

    private static void convertAction(String line, String element) {
        System.out.println(format(element));
        System.out.println(line);
        System.out.println();
    }
    
    
    private static String format(String element) {
       return  String.format("@DB(\"%s.%s\")", DB_NAME,  CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE,  element));
    }

}
