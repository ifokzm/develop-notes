package com.github.sailboat.common.util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * (1, 10)：代表 1 < x < 10；
 * (1, 10]：代表 1 < x <= 10；
 * [1, 10]：代表 1 <= x <= 10；
 * [1, 10)：代表 1 <= x < 10；
 * (1, ) 或 (1, ]：代表 x > 1；
 * [1, ] 或 [1, )：代表 x >= 1；
 * (, 10) 或 [, 10)：代表 x < 10；
 * (, 10] 或 [, 10]：代表 x <= 10；
 * 由于开闭区间是有一定规则的，所以开闭区间必须是合法的，除了**(、)、[、]、, 、数字** 之外，其他的字符都是不合法的，所以首先需要校验开闭区间的合法性；
 * 判定开闭区间合法后，再拆分出开闭区间的最小值和最大值；
 * 根据开闭区间是否包含[]，从而在判断时看是否需要添加 = 号
 * @author zhumeng
 */
public final class RangeUtil {
    /**
     * 开闭区间正则表达式
     */
    private static final Pattern NUM_RANGE_PATTERN = Pattern.compile("[\\[|\\(]\\s?\\d+\\s?,\\s?\\d+\\s?[\\)|\\]]");

    /**
     * 左半区间正则表达式
     */
    private static final Pattern LEFT_NUM_RANGE_PATTERN = Pattern.compile("[\\[|\\(]\\s?\\d+\\s?,\\s?[\\)|\\]]");

    /**
     * 右半区间正则表达式
     */
    private static final Pattern RIGHT_NUM_RANGE_PATTERN = Pattern.compile("[\\[|\\(],\\s?\\d+\\s?[\\)|\\]]");

    /**
     * 判断是否为有效的数字区间范围
     * 
     * @param numRange 数字区间
     * @return boolean
     */
    public static boolean isValidNumRange(String numRange) {
        return NUM_RANGE_PATTERN.matcher(numRange).matches()
                || LEFT_NUM_RANGE_PATTERN.matcher(numRange).matches()
                || RIGHT_NUM_RANGE_PATTERN.matcher(numRange).matches();
    }

    /**
     * 判断数值是否在区间范围内
     * 
     * @param number 数值
     * @param numRange 开闭区间
     * @return boolean
     */
    public static boolean inNumRange(int number, String numRange) {
        Objects.requireNonNull(numRange);

        if (!isValidNumRange(numRange)) {
            return false;
        }

        String[] pairs = numRange.split(",");

        // 获取开闭区间的最小值和最大值
        List<String> rangeNums = Arrays.stream(pairs).map(str -> str.replaceAll("[(|)|\\[|\\]]", "").trim()).collect(Collectors.toList());
        Integer minValue = "".equals(rangeNums.get(0)) ? null : Integer.valueOf(rangeNums.get(0));
        Integer maxValue = "".equals(rangeNums.get(1)) ? null : Integer.valueOf(rangeNums.get(1));

        // 判定数值是否大于最小值
        boolean minMatched = (minValue == null) || (pairs[0].startsWith("[") ? number >= minValue : number > minValue);
        // 判定数值是否小于最大值
        boolean maxMatched = (maxValue == null) || (pairs[1].endsWith("]") ? number <= maxValue : number < maxValue);

        return minMatched && maxMatched;
    }

    public static void main(String[] args) {
        System.out.println(inNumRange(1, "(0, 2]"));
        System.out.println(inNumRange(1, "(, 2]"));
        System.out.println(inNumRange(1, "(1, 4]"));
        System.out.println(inNumRange(1, "(0, ]"));
    }

}
