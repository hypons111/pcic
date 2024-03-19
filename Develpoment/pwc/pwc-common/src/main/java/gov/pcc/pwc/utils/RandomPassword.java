package gov.pcc.pwc.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomPassword {

        private static final String lowStr = "abcdefghijklmnopqrstuvwxyz";
        private static final String specialStr = "~!@#$%^&*()_+/-=[]{};:'<>?.";
        private static final String numStr = "0123456789";

    // 指定長度，隨機生成複雜密碼
    public static String getRandomPwd(int num){
        List<Character> list = new ArrayList<>(num);
        list.add(getLowChar());
        list.add(getUpperChar());
        list.add(getNumChar());
        list.add(getSpecialChar());

        for (int i = 4; i < num; i++) {
            SecureRandom random = new SecureRandom();
            int funNum = random.nextInt(4);
            char AA =  getRandomChar(funNum);
            list.add(AA);
        }

        Collections.shuffle(list);
        StringBuilder stringBuilder = new StringBuilder(list.size());
        for (Character c : list) {
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }

        // 隨機獲取字符串字符
        private static char getRandomChar (String str){
            SecureRandom random = new SecureRandom();
            return str.charAt(random.nextInt(str.length()));
        }

        // 隨機獲取小寫字符
        private static char getLowChar() {
            return getRandomChar(lowStr);
        }

        // 隨機獲取大寫字符
        private static char getUpperChar () {
            return Character.toUpperCase(getLowChar());
        }

        // 隨機獲取數字字符
        private static char getNumChar () {
            return getRandomChar(numStr);
        }

        // 隨機獲取特殊字符
        private static char getSpecialChar () {
            return getRandomChar(specialStr);
        }

        //指定調用字符函數
        private static char getRandomChar (int funNum){
            switch (funNum) {
                case 0:
                    return getLowChar();
                case 1:
                    return getUpperChar();
                case 2:
                    return getNumChar();
                default:
                    return getSpecialChar();
            }
        }


}