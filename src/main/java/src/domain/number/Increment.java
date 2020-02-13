package src.domain.number;

public class Increment {

    public static void main(String... args) {

        long i = 0;
        long b = i++;
        System.out.println(b);
        i = 0;
        long c = ++i;
        System.out.println(c);
    }
}
