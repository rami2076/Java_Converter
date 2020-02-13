package src.domain.tools.create.method;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CallMethodCreatorV5 {
    //下記入力
    // 棚卸番号 
    // info.setStocktakingNumber(meisaiInfo.getStocktakingNumber());
    /**
     * 
     * 入力は以下の通り。
    
     * 
     * または
     * q->終了。
     * または
     * その他の文字->そのまま出力。
     * 
     * 
     * 変換
     * 
     * index1 
     * 
     * 
     * 処理
     * それぞれの文字列の区切り文字「space」をすべて1byteにする。
     * spaceでsplit
     * index0は空文字
     * index1は識別子(private)
     * index2はsignature
     * index3はフィールド名->変換info.setXxx(this.xxx.getValue());
     * 
     * 
     * 
     * 
     * @param args
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            String line = sc.nextLine().replaceFirst("^\\s+", "");
            line = line.trim();
            String[] array = line.split("\\s+");

            if (array[0].equals("q")) {
                break;
            }

            if (line.startsWith("info")) {
                convertAction(line);
            } else {
                System.out.println(line);
            }

        }

        sc.close();
    }

    private static void convertAction(String line) {

        String replaceLine = line.replace("info", "this");
        replaceLine = replaceLine.replace("meisaiInfo", "info");

        if (line.contains("null")) {
            Pattern pattern = Pattern.compile("info.set.*(?=\\()");
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            String item = matcher.group();
            item = item.replace("set", "get");
            String ifString = format(item);
            System.out.println(ifString);
            System.out.println(replaceLine);
            System.out.println("}");
        } else {
            System.out.println(replaceLine);

        }

    }

    private static String format(String element) {
        return String.format("if ( Objects.isNull(%s()) ){", element);
    }

}
