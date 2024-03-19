package gov.pcc.pwc.utils;

import java.util.ArrayList;
import java.util.Locale;

public final class PccOrgIdUtil {

    private static final String APPLICATION_NAME = "PWC";

    private PccOrgIdUtil() {}

//【轉換邏輯】
//    最後一碼移除
//    第一碼保留
//    中間2碼為一組，轉換為新代碼內容
//
//    如果都是數字，轉換為數字內容
//    如果有非數字，保留內容
//    如果為00，直接忽略
//    新代碼之間以「.」連接
//
//    e.g 國立台灣大學 A09510000Q
//    拆解 A 09 51 00 00 Q
//    轉換結果 A.9.51

    public static String convertOrgId2PccOrgId(String orgId) {
        StringBuilder pccOrgId = new StringBuilder();
        String newOrgId;
        ArrayList<String> middleStringList = new ArrayList<>();
        String lastString = "";
        StringBuilder newMiddleString = new StringBuilder();
        //    整理傳入字串並移除最後一碼
        orgId = orgId.replaceAll(" ", "");
        newOrgId = orgId.toUpperCase(Locale.ROOT).substring(0, orgId.length() - 1);
        //    第一碼保留進最後結果
        pccOrgId.append(newOrgId.substring(0,1));
        //    中間2碼為一組，轉換為新代碼內容
        newOrgId = newOrgId.substring(1); //移除第一碼

        int middleStringLength = newOrgId.length();

        // 判斷中間字是否為奇數，若是則把最後一個字判斷不為0時單獨保留
        if (middleStringLength % 2 == 1) {
            lastString = newOrgId.substring(middleStringLength - 1);
            if ("0".equals(lastString)) {
                newOrgId = newOrgId.substring(0, middleStringLength - 1);
            }
            middleStringLength = newOrgId.length() - 1;
        }

        for (int i = 0; i < middleStringLength; i+= 2) {
            middleStringList.add(newOrgId.substring(i, i + 2));
        }

        // 新中間字串
        middleStringList.forEach(middleString -> {
            if ("00".equals(middleString)) {
            } else if (middleString.matches("\\d+")) {
                int middleStringInt = Integer.parseInt(middleString);
                newMiddleString.append(middleStringInt + ".");
            } else {
                newMiddleString.append(middleString + ".");
            }
        });

        //　組合新代碼
        pccOrgId.append('.').append(newMiddleString).append(lastString);
        if (pccOrgId.toString().endsWith(".")) {
            pccOrgId.deleteCharAt(pccOrgId.length() - 1);
        }
        return pccOrgId.toString();
    }

    public static void main(String[] args) {
        System.out.println(convertOrgId2PccOrgId("A61001100A"));
        System.out.println(convertOrgId2PccOrgId("A09510000Q"));
        System.out.println(convertOrgId2PccOrgId("349010700A"));
    }

}
