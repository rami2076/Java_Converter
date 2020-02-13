package src.domain.tools.create.method;

import java.util.Scanner;

import com.google.common.base.CaseFormat;

public class FieledCreateV4 {

    public static String DB_NAME ;
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //FACTORY_CODE                                      工場コード
        System.out.println("テーブル名を入力");
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
            
            convertAction(array[1], array[0]);
            
            

        }

        sc.close();
    }

    
    private static void convertAction(String comment, String element) {
        
        element=  CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, element);
          
          comment = String.join("", "/**", " ", comment , " */");
          String field = String.join(" ", "private", "String", element,";");
    
          System.out.println(comment);
          addAnotation(field,element);
      }
    
    
    private static void addAnotation(String line, String element) {
        System.out.println(format(element));
        System.out.println(line);
        System.out.println();
    }
    
    
    private static String format(String element) {
       return  String.format("@DB(\"%s.%s\")", DB_NAME,  CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE,  element));
    }

}
