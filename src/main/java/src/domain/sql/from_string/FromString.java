package src.domain.sql.from_string;

import java.util.StringJoiner;

public class FromString {

    public static void main(String[] args) {
        場所名と場所コード();
    }

    private static void 工場コードコンボ情報取得() {
        String sql = "SELECT FACTORY_CODE,FACTORY_NAME,FACTORY_NAME_RYAKU FROM PANEL.MS_FACTORY " +
                " WHERE IS_ACTIVE = 0 and OUTSIDE_FLAG = 0";
        System.out.println(sql);

    }

    private static void 対象月取得() {
        StringBuffer sb = new StringBuffer("SELECT ")
                .append("\n")
                .append("MS_MONTHLY_YEARS.MONTHLY_YEARS,MS_FACTORY.FACTORY_NAME ")
                .append("\n")
                .append("FROM MS_MONTHLY_YEARS ")
                .append("\n")
                .append("LEFT JOIN MS_FACTORY ON ")
                .append("\n")
                .append("MS_MONTHLY_YEARS.FACTORY_CODE = MS_FACTORY.FACTORY_CODE AND ")
                .append("\n")
                .append("MS_FACTORY.IS_ACTIVE = 0 ")
                .append("\n")
                .append("WHERE ")
                .append("\n")
                .append("MS_FACTORY.FACTORY_CODE = ? ");

        System.out.println(sb.toString());
    }

    private static void 材料系月報用資産区分() {
        StringBuffer sb = new StringBuffer("SELECT ")
                .append("ASSET_TYPE, UKEHARAI_CODE, DATA_CHARACTERISTICS1 ")
                .append("\n")
                .append("FROM MS_ZAIRYO_UKEHARAI WHERE ")
                .append("\n")
                .append("FACTORY_CODE = '").append("?").append("' AND ")
                .append("\n")
                .append("UKEHARAI_CODE IS NOT NULL AND DATA_CHARACTERISTICS1 IS NOT NULL ")
                .append("\n")
                .append("GROUP BY ASSET_TYPE, UKEHARAI_CODE, DATA_CHARACTERISTICS1 ")
                .append("\n")
                .append("UNION ")
                .append("\n")
                .append("SELECT ")
                .append("\n")
                .append("ASSET_TYPE, UKEHARAI_CODE, DATA_CHARACTERISTICS2 ")
                .append("\n")
                .append("FROM MS_ZAIRYO_UKEHARAI WHERE ")
                .append("\n")
                .append("FACTORY_CODE = '").append("? ").append("' AND ")
                .append("\n")
                .append("UKEHARAI_CODE IS NOT NULL AND DATA_CHARACTERISTICS2 IS NOT NULL ")
                .append("\n")
                .append("GROUP BY ASSET_TYPE, UKEHARAI_CODE, DATA_CHARACTERISTICS2 ")
                .append("\n")
                .append("UNION ")
                .append("\n")
                .append("SELECT ")
                .append("\n")
                .append("ASSET_TYPE, UKEHARAI_CODE, DATA_CHARACTERISTICS ")
                .append("\n")
                .append("FROM TS_ZAIRYO_ERROR_SEND WHERE ")
                .append("FACTORY_CODE = '").append("? ").append("' AND ")
                .append("\n")
                .append("UKEHARAI_CODE IS NOT NULL AND DATA_CHARACTERISTICS IS NOT NULL ")
                .append("\n")
                .append("GROUP BY ASSET_TYPE, UKEHARAI_CODE, DATA_CHARACTERISTICS ");

        System.out.println(sb.toString());

    }

    public static void パネル製品系月報明細書情報取得() {
        StringBuffer sb = new StringBuffer("SELECT ")
                .append("\n")
                .append("FACTORY_CODE, UKEHARAI_TYPE, SLIP_NUMBER, ACCOUNT_PLACE_CODE,")
                .append("\n")
                .append("ATTRIBUTION_PLACE_CODE, COST_SECTION,PRODUCTION_COST_SECTION,")
                .append("\n")
                .append("WAREHOUSE_CODE, DESTINATION_WAREHOUSE_CODE, CUSTOMER_CODE,AREA_CODE,")
                .append("\n")
                .append("ORDER_NUMBER, ORDER_SUB_NUMBER, DESTINATION_AREA_CODE, DESTINATION_ORDER_NUMBER,")
                .append("\n")
                .append("DESTINATION_ORDER_SUB_NUMBER, INSTALLMENT_TYPE, STRAGE_IN_OUT_DATE,")
                .append("\n")
                .append("TRADE_NAME,PRODUCT_TYPE, SML_DIVISION_CODE, TRADE_CODE, SPECIAL_SIZE,")
                .append("\n")
                .append("ARRAY_CODE, DESTINATION_SML_DIVISION_CODE, DESTINATION_TRADE_CODE,")
                .append("\n")
                .append("DESTINATION_SPECIAL_SIZE, DESTINATION_ARRAY_CODE, COST_CLASS_CODE,");
    }

    public static void 景観製品系月報明細書情報取得() {
        StringBuffer sb = new StringBuffer("SELECT ");
        sb.append("FACTORY_CODE, UKEHARAI_TYPE, SLIP_NUMBER, ACCOUNT_PLACE_CODE, ");
        sb.append("ATTRIBUTION_PLACE_CODE, COST_SECTION,PRODUCTION_COST_SECTION, ");
        sb.append("WAREHOUSE_CODE, DESTINATION_WAREHOUSE_CODE, CUSTOMER_CODE,AREA_CODE, ");
        sb.append("ORDER_NUMBER, ORDER_SUB_NUMBER, DESTINATION_AREA_CODE, DESTINATION_ORDER_NUMBER, ");
        sb.append("DESTINATION_ORDER_SUB_NUMBER, INSTALLMENT_TYPE, STRAGE_IN_OUT_DATE, ");
        sb.append("TRADE_NAME,PRODUCT_TYPE, SML_DIVISION_CODE, TRADE_CODE, SPECIAL_SIZE, ");
        sb.append("ARRAY_CODE, DESTINATION_SML_DIVISION_CODE, DESTINATION_TRADE_CODE, ");
        sb.append("DESTINATION_SPECIAL_SIZE, DESTINATION_ARRAY_CODE, COST_CLASS_CODE, ");
        sb.append("ACCOUNT_TITLE_BIG, ACCOUNT_TITLE_MID, ACCOUNT_TITLE_SML, QTY, ");
        sb.append("WEIGHT, UNIT_PRICE, PRICE, ORDER_MONEY, YIELD,SALE_RECOGNITION_TYPE, ");
        sb.append("SALES_DATA_TYPE, STYLE_CODE, ARTICLE_NAME, END_SALES, DESTINATION_ACCOUNT_PLACE_CODE, ");
        sb.append("DESTINATION_ACCOUNT_TITLE_BIG, DESTINATION_ACCOUNT_TITLE_MID, DESTINATION_ACCOUNT_TITLE_SML, ");
        sb.append("BUDGET_NUMBER, REFERENCE_NUMBER,GROUP_CODE, SPECIAL_UKEHARAI_TYPE, COST_UNIT, ");
        sb.append("CREATING_AGENCY_SYSTEM,SEQ,ENTRY_TYPE  ");
        sb.append("FROM ");
        sb.append("TS_SEIHIN_SEND_TO_HEAD  ");
        sb.append("WHERE  ");
        sb.append("FACTORY_CODE = ? ");
        sb.append("AND COST_SECTION = ? ");
        sb.append("AND STRAGE_IN_OUT_DATE BETWEEN ? AND ? ");
        sb.append("ORDER BY ");
        sb.append("COST_CLASS_CODE, UKEHARAI_TYPE, PRODUCTION_COST_SECTION, ORDER_NUMBER, ORDER_SUB_NUMBER,");
        sb.append("SML_DIVISION_CODE, TRADE_CODE, SPECIAL_SIZE, SEQ  ");

    }

    public static void 棚卸処理年月日取得用() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("SELECT STOCK_TAKING_DATE, FACTORY_NAME ")
                .add("FROM MS_FACTORY ")
                .add("LEFT JOIN MS_MONTHLY_YEARS ON ")
                .add("MS_FACTORY.FACTORY_CODE = MS_MONTHLY_YEARS.FACTORY_CODE ")
                .add("WHERE MS_FACTORY.FACTORY_CODE = ? ")
                .add("AND MS_FACTORY.IS_ACTIVE = 0");

        System.out.println(joiner.toString());

    }

    //明細部出力用データ取得用PANEL
    public static void PANEL() {
        //panel

        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("SELECT ");
        joiner.add(
                "TIP.FACTORY_CODE, TIP.PLACE_CODE, MN.NAME1, TIP.PALET_NUMBER, 0 DEL_FLG, NVL(TIP.ORDER_NUMBER, ' ') ORDER_NUMBER, ");
        joiner.add("TIP.BUSINESS_NUMBER, TIP.PAGE_NO_OYA, TIP.PAGE_NO_KO, TIP.TRADE_TYPE, TIP.QUANTITY, ");
        joiner.add("TIP.STOCKTAKING_NUMBER, TIP.INPUT_SEQ, TIP.SECTION_CODE, TIP.PRODUCT_KIND, ");
        joiner.add("TIP.WRITE_WRONG_TYPE, // 指示数、書き損じ      ");
        joiner.add("SUM(NVL(TSP.QUANTITY, 0)) REMAINDER_QTY, SUM(NVL(TSP.STOCK_QTY, 0)) STOCK_QTY, ");
        joiner.add("TIP.INSTALLMENT_NUMBER, TIP.INSTALLMENT_SM_NUMBER ");
        joiner.add("FROM TS_INVENTORY_PANEL TIP ");
        joiner.add(
                "LEFT JOIN MS_NAME MN ON USE_TYPE = 97 AND NAME_CODE = PLACE_CODE AND IS_ACTIVE = 0 AND DELETE_FLG <> 1 ");
        joiner.add("LEFT JOIN TS_STOCK_PANEL TSP ON ");
        joiner.add("TSP.FACTORY_CODE = TIP.FACTORY_CODE AND ");
        joiner.add("TSP.ORDER_NUMBER = TIP.ORDER_NUMBER AND ");
        joiner.add("TSP.PAGE_NO_OYA = TIP.PAGE_NO_OYA AND ");
        joiner.add("TSP.PAGE_NO_KO = TIP.PAGE_NO_KO AND ");
        joiner.add("TO_CHAR(STOCK_DATE, 'yyyy/MM') = TO_CHAR(STOCKTAKING_DATE, 'yyyy/MM') ");
        joiner.add("WHERE ");
        joiner.add("TIP.FACTORY_CODE = '?kojoCd'").add("' AND ");
        joiner.add("TO_CHAR(TIP.STOCKTAKING_DATE, 'yyyy/MM') LIKE '").add("'?stockDate'").add("%' AND ");
        joiner.add("TIP.WAREHOUSE_CODE = '").add("'?warehouseCode'").add("' AND TIP.PRODUCT_KIND <> '4' ");
        joiner.add("if (!orderNumber.equals(空文字)) {");
        joiner.add("AND TIP.ORDER_NUMBER = '").add("?orderNumber");
        joiner.add("}");
        joiner.add("if (!stocktakingNumberS.equals(空白) {");
        joiner.add("AND TIP.STOCKTAKING_NUMBER >= '").add("?stocktakingNumberS ");
        joiner.add("}");
        joiner.add("if (!stocktakingNumberE.equals(空白)) {");
        joiner.add("AND TIP.STOCKTAKING_NUMBER <= '").add("?stocktakingNumberE");
        joiner.add("}");

        joiner.add("if (installmentNumber != 0) {");
        joiner.add("AND TIP.INSTALLMENT_NUMBER = ").add("?installmentNumber");
        joiner.add("}");
        joiner.add("if (installmentSmNumber != 0) {");
        joiner.add("AND TIP.INSTALLMENT_SM_NUMBER = ").add("?installmentSmNumber");
        joiner.add("}");
        joiner.add("if (!paletNumber.equals(空文字)) {");
        joiner.add("AND TIP.PALET_NUMBER = '").add("?paletNumber");
        joiner.add("}");
        joiner.add("GROUP BY ");
        joiner.add(
                "TIP.FACTORY_CODE, TIP.PLACE_CODE, MN.NAME1, TIP.PALET_NUMBER, TIP.ORDER_NUMBER, TIP.BUSINESS_NUMBER, ");
        joiner.add(
                "TIP.PAGE_NO_OYA, TIP.PAGE_NO_KO, TIP.TRADE_TYPE, TIP.QUANTITY, TIP.STOCKTAKING_NUMBER, TIP.INPUT_SEQ, ");
        joiner.add("TIP.SECTION_CODE, TIP.PRODUCT_KIND, ");
        joiner.add("TIP.WRITE_WRONG_TYPE, TIP.INSTALLMENT_NUMBER, TIP.INSTALLMENT_SM_NUMBER ");

        joiner.add(" if (sort == 0) {// 棚卸No順");
        joiner.add("ORDER BY TIP.STOCKTAKING_NUMBER, TIP.INPUT_SEQ ");
        joiner.add("} else if (sort == 1) { // オーダーNo順");
        joiner.add("ORDER BY TIP.ORDER_NUMBER, TIP.INPUT_SEQ ");
        joiner.add("}");

        System.out.println(joiner.toString());
    }

    public static void 明細部出力用データ取得用玄米保冷庫() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add("WITH INOUT AS (");
        joiner.add("SELECT ");
        joiner.add("FACTORY_CODE, FA_ORDER_NUMBER, ARRAY_CODE, SUM(IN_QTY) IN_QTY, SUM(OUT_QTY) OUT_QTY ");
        joiner.add("FROM (");
        joiner.add("SELECT FACTORY_CODE, FA_ORDER_NUMBER, ARRAY_CODE, SUM(QTY) IN_QTY, 0 OUT_QTY ");
        joiner.add("FROM TS_SEIHIN_STRAGE_IN ");
        joiner.add("WHERE FACTORY_CODE = ?factoryCode'").add("' AND ");
        joiner.add("TO_CHAR(STRAGE_IN_OUT_DATE, 'yyyy/MM') LIKE ?stockDate%'").add(" AND ARRAY_CODE = 'KOME' ");
        joiner.add("GROUP BY FACTORY_CODE, FA_ORDER_NUMBER, ARRAY_CODE ");
        joiner.add("UNION ALL ");
        joiner.add("SELECT FACTORY_CODE, FA_ORDER_NUMBER, ARRAY_CODE, 0  IN_QTY, SUM(QTY) OUT_QTY ");
        joiner.add("FROM TS_SEIHIN_TAKE_OUT ");
        joiner.add("WHERE FACTORY_CODE = ?factoryCode").add("' AND ");
        joiner.add("TO_CHAR(STRAGE_IN_OUT_DATE, 'yyyy/MM') LIKE ?stockDate%").add(" AND ARRAY_CODE = 'KOME' ");
        joiner.add("GROUP BY FACTORY_CODE, FA_ORDER_NUMBER, ARRAY_CODE ");
        joiner.add(") ");
        joiner.add("GROUP BY FACTORY_CODE, FA_ORDER_NUMBER, ARRAY_CODE ");
        joiner.add(") ");
        joiner.add("SELECT ");
        joiner.add("TIP.FACTORY_CODE, TIP.PLACE_CODE, MN.NAME1, TIP.PALET_NUMBER, 0 DEL_FLG, TIP.ORDER_NUMBER, ");
        joiner.add("TIP.BUSINESS_NUMBER, TIP.PAGE_NO_OYA, TIP.PAGE_NO_KO, TIP.TRADE_TYPE, TIP.QUANTITY, ");
        joiner.add("TIP.STOCKTAKING_NUMBER, TIP.INPUT_SEQ, TIP.SECTION_CODE, TIP.PRODUCT_KIND, ");
        joiner.add("NVL(INOUT.IN_QTY, 0) IN_QTY, NVL(INOUT.OUT_QTY, 0) OUT_QTY, ");
        joiner.add("SUM(NVL(TSK.QUANTITY, 0)) REMAINDER_QTY, SUM(NVL(TSK.STOCK_QTY, 0)) STOCK_QTY ");
        joiner.add("FROM TS_INVENTORY_PANEL TIP ");
        joiner.add(
                "LEFT JOIN MS_NAME MN ON USE_TYPE = 97 AND NAME_CODE = PLACE_CODE AND IS_ACTIVE = 0 AND DELETE_FLG <> 1 ");
        joiner.add("LEFT JOIN TS_STOCK_KOMEKO TSK ON ");
        joiner.add("TSK.FACTORY_CODE = TIP.FACTORY_CODE AND ");
        joiner.add("TSK.ORDER_NUMBER = TIP.ORDER_NUMBER AND ");
        joiner.add("TO_CHAR(TSK.STOCK_DATE, 'yyyy/MM') = TO_CHAR(TIP.STOCKTAKING_DATE, 'yyyy/MM') ");
        joiner.add("LEFT JOIN INOUT ON ");
        joiner.add("TIP.FACTORY_CODE = INOUT.FACTORY_CODE AND ");
        joiner.add("TIP.ORDER_NUMBER = INOUT.FA_ORDER_NUMBER ");
        joiner.add("WHERE ");
        joiner.add("TIP.FACTORY_CODE = ?factoryCode").add(" AND ");
        joiner.add("TO_CHAR(TIP.STOCKTAKING_DATE, 'yyyy/MM') LIKE ?stockDate%").add("AND ");
        joiner.add("TIP.WAREHOUSE_CODE = ?warehouseCode").add("AND TIP.PRODUCT_KIND = '4' ");
        joiner.add("if (!orderNumber.equals(空文字)) {");
        joiner.add("AND TIP.ORDER_NUMBER = ?orderNumber");
        joiner.add("}");
        joiner.add("if (!stocktakingNumberS.equals(void)) {");
        joiner.add("AND TIP.STOCKTAKING_NUMBER >= ?stocktakingNumberS'");
        joiner.add("}");
        joiner.add("if (!stocktakingNumberE.equals(void)) {");
        joiner.add("AND TIP.STOCKTAKING_NUMBER <= ?stocktakingNumberE '");
        joiner.add("}");
        joiner.add("GROUP BY ");
        joiner.add(
                "TIP.FACTORY_CODE, TIP.PLACE_CODE, MN.NAME1, TIP.PALET_NUMBER, TIP.ORDER_NUMBER, TIP.BUSINESS_NUMBER, ");
        joiner.add(
                "TIP.PAGE_NO_OYA, TIP.PAGE_NO_KO, TIP.TRADE_TYPE, TIP.QUANTITY, TIP.STOCKTAKING_NUMBER, TIP.INPUT_SEQ, ");
        joiner.add("TIP.SECTION_CODE, TIP.PRODUCT_KIND, IN_QTY, OUT_QTY ");

        joiner.add("if (sort == 0) { // 棚卸No順");
        joiner.add("ORDER BY TIP.STOCKTAKING_NUMBER, TIP.INPUT_SEQ ");
        joiner.add("} else if (sort == 1) {  オーダーNo順");
        joiner.add("ORDER BY TIP.ORDER_NUMBER, TIP.INPUT_SEQ ");
        joiner.add("}");
        System.out.println(joiner.toString());

    }

    public static void パネル枚数の取得用SQL() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("SELECT NVL(PANEL_NUMBER, 0) PANEL_NUMBER ")
                .add("FROM TS_PANEL_DATA ")
                .add("WHERE FACTORY_CODE = ? ")
                .add("AND ARTICLE_YEAR = ? ")
                .add("AND ARTICLE_NUMBER = ? ")
                .add("AND ORDER_NUMBER = ? ")
                .add("AND INSTALLMENT_NUMBER = ? ")
                .add("AND INSTALLMENT_SM_NUMBER = ? ")
                .add("AND PAGE_NO_OYA = ? ");
        System.out.println(joiner.toString());
    }

    public static void 物件情報の取得用SQL() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("SELECT ")
                .add("ORDER_NUMBER, ARTICLE_YEAR, ARTICLE_NUMBER ")
                .add("FROM TS_ORDER_INFO ")
                .add("WHERE TS_ORDER_INFO.FACTORY_CODE = ? ")
                .add("AND TS_ORDER_INFO.ORDER_NUMBER = ? ")
                .add("ORDER BY ARTICLE_YEAR DESC, ARTICLE_NUMBER DESC");
        System.out.println(joiner.toString());

    }

    public static void 場所名と場所コード() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("SELECT ")
                .add("NAME_CODE,NAME1 ")
                .add("FROM ")
                .add("MS_NAME ")
                .add("WHERE ")
                .add("USE_TYPE=97 AND ")
                .add("DELETE_FLG <>1 AND ")
                .add("IS_ACTIVE = 0 ")
                .add("ORDER BY ")
                .add("NAME_CODE");
        System.out.println(joiner.toString());

    }

}
