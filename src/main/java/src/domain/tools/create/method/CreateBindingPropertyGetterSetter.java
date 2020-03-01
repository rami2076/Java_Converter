package src.domain.tools.create.method;

import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateBindingPropertyGetterSetter {

    //入力は以下
    /** 工場コード */
    //@BindingProperty
    //private StringProperty factoryCode;

    //出力
    //public String getHaraidashi() {
    //    return haraidashi.get();
    //}
    //
    //public void setHaraidashi(String haraidashi) {
    //    this.haraidashi.set(haraidashi);
    //}

    /**
     * 
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
            String[] array = line.split("\\s+");

            if (array[0].equals("q")) {
                break;
            }

            if (array.length == 0 || array.length == 1) {
                continue;
            }

            switch (array[0]) {
            case "private":
                convertAction(array[1], array[2]);
                break;
            default:
                defaultAction(line);
                break;
            }
        }

        sc.close();
    }

    private static void convertAction(String signature, String field) {

        //フィールドの成形
        String normalizeField = field.replace(";", "");
        //頭文字を大文字に変更
        String headUpperField = headUpper(normalizeField);

        if (signature.equals("StringProperty")) {
            signature = "String";
        } else {

            Pattern pattern = Pattern.compile("(?<=<).*(?=>)");
            Matcher matcher = pattern.matcher(signature);
            matcher.find();
            String genericsClass = matcher.group();
            signature = genericsClass;

    
        }
        
        
        String getter = getterFormat(signature, normalizeField, headUpperField);
        String setter = setterFormat(signature, normalizeField, headUpperField);

        System.out.println(getter);
        System.out.println();
        System.out.println(setter);
        System.out.println();

        
        

    }

    private static void defaultAction(String line) {
        //出力しない
    }

    private static String headUpper(String element) {
        String capitalizedName = Character.toTitleCase(element.charAt(0)) + element.substring(1);
        return capitalizedName;
    }

    private static String getterFormat(String signature, String field, String headUpperField) {

        StringJoiner joiner = new StringJoiner("\n");
        String line1 = String.format("public %s get%s(){", signature, headUpperField);
        String line2 = String.format("return %s.get();", field);
        String line3 = "}";

        joiner.add(line1).add(line2).add(line3);

        return joiner.toString();
    }

    private static String setterFormat(String signature, String field, String headUpperField) {

        StringJoiner joiner = new StringJoiner("\n");
        String line1 = String.format("public void set%s(%s %s){", headUpperField, signature, field);
        String line2 = String.format("this.%s.set(%s);", field, field);
        String line3 = "}";

        joiner.add(line1).add(line2).add(line3);
        return joiner.toString();
    }

}
