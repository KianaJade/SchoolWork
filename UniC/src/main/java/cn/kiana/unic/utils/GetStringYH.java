package cn.kiana.unic.utils;

public class GetStringYH {
    public static String getRightStr(String input) {
        int startIndex = input.indexOf("\"");
        if (startIndex != -1) {
            int endIndex = input.indexOf("\"", startIndex + 1);
            if (endIndex != -1) {
                // 提取双引号之间的内容
                String value = input.substring(startIndex + 1, endIndex);
                return value;
            }
        }
        return input;
    }
}
