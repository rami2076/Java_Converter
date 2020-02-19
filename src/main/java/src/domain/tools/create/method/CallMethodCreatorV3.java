package src.domain.tools.create.method;

import java.util.Scanner;

public class CallMethodCreatorV3 {

    //下記が入力
    // /** 書損じ区分 */
    //@DB("TS_INVENTORY_PANEL.WRITE_WRONG_TYPE;")
    //private BigDecimal writeWrongType;

    //下記が出力
    //  >// 書損じ区分
    //  >info.setXxx(this.xxx.getValue());
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

    private static void convertAction(String signature, String element) {

        element = element.replace(";", "");
        String headUpperElement = headUpper(element);

        String result = String.join("", "info.", "set", headUpperElement, "(meisaiInfo.get", headUpperElement, "());");

        System.out.println(result);
    }

    private static String headUpper(String element) {
        String capitalizedName = Character.toTitleCase(element.charAt(0)) + element.substring(1);
        return capitalizedName;
    }

    private static void defaultAction(String line) {
       String normal= line.trim();
        if ( normal.isEmpty()  ) {
            return;
        }else if(line.startsWith("/")){
            line = line.replace("/**", "//");
            line = line.replace("*/", "");
            System.out.println(line);
        }else {
            return;
        }
            
        


    }

}
