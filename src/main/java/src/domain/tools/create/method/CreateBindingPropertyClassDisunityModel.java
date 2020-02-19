package src.domain.tools.create.method;

import java.util.Scanner;

public class CreateBindingPropertyClassDisunityModel {

    //入力は以下
    /** 工場コード */
    // private String factoryCode;

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
                convertAction(array[0], array[1], array[2]);
                break;
            default:
                defaultAction(line);
                break;
            }
        }

        sc.close();
    }

    private static void convertAction(String modification, String signature, String beanField) {
        final String annotation = annotationFormat("", beanField);

        if (signature.equals("String")) {
            signature = "StringProperty";
        } else {
            signature = signatureFormat(signature);
        }

        String line = String.join(" ", modification, signature, beanField);

        System.out.println(annotation);
        System.out.println(line);
        System.out.println();

    }

    private static void defaultAction(String line) {
        //そのまま出力
        System.out.println(line);
    }

    private static String signatureFormat(String signature) {
        return String.format("ObjectProperty<%s>", signature);

    }

    private static String annotationFormat(String controllerField, String beanField) {

        controllerField = controllerField.replace(";", "");
        beanField = beanField.replace(";", "");

        //そのまま出力
        return String.format("@BindingProperty(controller=\"%s\",bean=\"%s\")", controllerField, beanField);
    }

}
