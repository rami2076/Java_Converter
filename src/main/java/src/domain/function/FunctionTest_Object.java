package src.domain.function;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionTest_Object {

    private   String inputOrBlank2(Object element ){
        return  Optional.ofNullable(element).map(Object::toString).orElse("");
    }
    
    
    private   <T extends Object>  String inputOrBlank(T element ){
        return  Optional.ofNullable(element).map(T::toString).orElse("");
    }
    
    private   <T extends Object>  String inputOrZero(T element ){
        return  Optional.ofNullable(element).map(T::toString).orElse("0");
    }
    
    private  static <T extends Object,U extends Object>  String inputOrElse(T element ,U other){
        return  Optional.ofNullable(element).map(T::toString).orElse(other.toString());
    }
    
    
    
    public static void main(String... strings) {
        FunctionTest_Object self =     new FunctionTest_Object();
      String a=  self.<BigDecimal>inputOrBlank(BigDecimal.ONE);
      String b=  self.<String>inputOrZero("");

      String c=  self.<BigDecimal>inputOrZero(null);
      String d=  self.<String>inputOrZero(null);
      
      
      System.out.println(a);
      System.out.println(b);
      System.out.println(c);
      System.out.println(d);

      
      String e=  FunctionTest_Object.<String,String>inputOrElse("","O");
      String f=  FunctionTest_Object.<BigDecimal,BigDecimal>inputOrElse(BigDecimal.ONE,BigDecimal.ONE);

      String g=  FunctionTest_Object.<String,String>inputOrElse(null,"O");
      String h=  FunctionTest_Object.<BigDecimal,BigDecimal>inputOrElse(null,BigDecimal.ONE);
      
      System.out.println(e);
      System.out.println(f);
      System.out.println(g);
      System.out.println(h);
      
      
      System.out.println( self.inputOrBlank2("aaa"));
      
      Function<Object, String> orBlank = number ->
      Optional.ofNullable(number)
      .map(Object::toString)
      .orElse("");
      
      String j = orBlank.apply("11");
      System.out.println(j);
      
      
      BiFunction<Object, String, String> inputOrElse =
              (input, other) -> Optional.ofNullable(input)
                  .map(Object::toString)
                  .orElse(other);

          //nullの場合、未入力値を設定する。
          String i = inputOrElse.apply(null, "");
          String o = inputOrElse.apply(null, "");
          String p = inputOrElse.apply(null, "0");
          String u = inputOrElse.apply(null, "0");
          String t = inputOrElse.apply(null, "");
      
          System.out.println(i);
          System.out.println(o);
          System.out.println(p);
          System.out.println(u);
          System.out.println(t);
      
    }

}
