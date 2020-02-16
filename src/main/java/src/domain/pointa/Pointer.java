package src.domain.pointa;

public class Pointer {
    public static void main(String[] args) {
        int i = 5;
        // #start unsafe# http://java.sun.com\u000a\u002f\u002a
        int *ip = &i;
        // ポインタの内容に3を加算
        *ip += 3;
        // 演算結果をiに代入
        i = *ip;
        // #end unsafe# http://java.sun.com\u002a\u002fi+=\u0033\u003b
        System.out.println(i);
    }
}