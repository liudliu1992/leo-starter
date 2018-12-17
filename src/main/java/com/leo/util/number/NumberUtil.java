package com.leo.util.number;

import com.leo.util.lang.NumberConsts;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * @author 刘绍林
 * @description
 * @time 2017/4/21
 */
public class NumberUtil {
    /**
     * 将元数据前补零，补后的总长度为指定的长度，以字符串的形式返回
     * 0 指前面补充零
     * formatLength 字符总长度为 formatLength
     * d 代表为正数。
     *
     * @param source       元数据
     * @param formatLength 长度
     * @return 重组后的数据
     */
    public static String frontCompWithZero(String source, int formatLength) {
        return frontCompWithZero(Integer.parseInt(source), formatLength);
    }

    /**
     * 将元数据前补零，补后的总长度为指定的长度，以字符串的形式返回
     * 0 指前面补充零
     * formatLength 字符总长度为 formatLength
     * d 代表为正数。
     *
     * @param source       元数据
     * @param formatLength 长度
     * @return 重组后的数据
     */
    public static String frontCompWithZero(int source, int formatLength) {
        return String.format("%0" + formatLength + "d", source);
    }

    /**
     * 将数字按千分位输出
     *
     * @param sourceNumber 需要修改的数字
     * @return 加上千分位后的数
     */
    public static String qianFenWei(Number sourceNumber) {
        return NumberFormat.getNumberInstance().format(sourceNumber);
    }

    /**
     * 设置两个数相乘结果4舍6入5成双
     *<p>默认精度为2,保留2为小数</p>
     * @param multiplier 乘数
     * @param multiplied 被乘数
     * @return accuracy
     */
    public static BigDecimal calculateBonuses(BigDecimal multiplier, BigDecimal multiplied) {
        return calculateBonuses(multiplier,multiplied, NumberConsts.TWO);
    }

    public static BigDecimal calculateBonuses(BigDecimal multiplier, BigDecimal multiplied,int scale) {
        return multiplied.multiply(multiplier).setScale(scale, BigDecimal.ROUND_HALF_EVEN);
    }

}
