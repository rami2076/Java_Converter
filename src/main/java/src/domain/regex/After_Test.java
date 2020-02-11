package src.domain.regex;

import java.util.regex.Pattern;

public class After_Test {
    public static void main(String[] args) {
         String target = "ああPaa";
        Pattern pattern = Pattern.compile("^..P.*");
        System.out.println("aa" + "ああPaa".matches("^..P.*"));
        boolean result= Pattern.matches("^..P.*",target);
        System.out.println(result);
        
        
      
    }
}
