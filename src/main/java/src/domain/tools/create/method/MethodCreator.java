package src.domain.tools.create.method;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MethodCreator {

    /**
     * 置換文字 末尾
     */
    public static final String TAIL = ".setDisable(true);";
    /**
     * 置換文字 先頭
     */
    public static final String PRE = "//";
    /**
     * 置換文字 中間
     */
    public static final String MIDDLE = "\nthis.";

    public static final Pattern TAIL_PATTERN = Pattern.compile("\\|$");
    public static final Pattern PRE_PATTERN = Pattern.compile("^\\|");
    public static final Pattern MIDDLE_PATTERN = Pattern.compile("\\|");

    /**
     * 　入力フォーマット
     * |場所|basho|
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        while (true) {
            String line = sc.nextLine()
                    .replaceFirst("^\\s+", "")
                    .replaceFirst("\\s+$", "");

            if (line.equals("q")) {
                break;
            }

            if (line.length() == 0) {
                continue;
            }

            Arrays.asList(line).stream().map(MethodCreator::tail)
                    .map(MethodCreator::pre)
                    .map(MethodCreator::middle)
                    .forEach(System.out::println);

        }


        sc.close();
    }

    /**
     * @param sentense
     * @return
     */
    private static String tail(String sentense) {
        return TAIL_PATTERN.matcher(sentense).replaceFirst(TAIL);
    }

    /**
     * @param sentense
     * @return
     */
    private static String pre(String sentense) {
        return PRE_PATTERN.matcher(sentense).replaceFirst(PRE);
    }

    /**
     * @param sentense
     * @return
     */
    private static String middle(String sentense) {
        return MIDDLE_PATTERN.matcher(sentense).replaceFirst(MIDDLE);
    }

}
