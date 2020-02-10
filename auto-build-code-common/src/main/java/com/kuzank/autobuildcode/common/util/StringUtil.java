package com.kuzank.autobuildcode.common.util;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/9/16
 */
public class StringUtil {

    public static final String EMPTY = "";
    public static final String NULL_STRING = "null";

    /**
     * 将驼峰名称的名称修改为下划线的数据库模式
     *
     * @param camelCaseName 驼峰名称
     * @return
     */
    public static String camelCase2Underscore(String camelCaseName) {

        StringBuilder result = new StringBuilder();
        if (camelCaseName != null && camelCaseName.length() > 0) {
            result.append(camelCaseName.substring(0, 1).toLowerCase());
            for (int i = 1; i < camelCaseName.length(); i++) {
                char ch = camelCaseName.charAt(i);
                if (Character.isUpperCase(ch)) {
                    result.append("_");
                    result.append(Character.toLowerCase(ch));
                } else {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }

    public static final boolean notNull_Empty(String str) {
        return str != null && str.length() > 0;
    }

    public static StringBuilder removeLastComma(StringBuilder builder) {

        if (builder == null || builder.length() == 0) {
            return new StringBuilder();
        }

        int index = builder.lastIndexOf(",");

        if (index == -1) {
            return builder;
        } else {
            builder.deleteCharAt(index);
            return builder;
        }
    }
}
