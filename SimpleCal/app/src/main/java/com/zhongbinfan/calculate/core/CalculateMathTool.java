package com.zhongbinfan.calculate.core;

import java.math.BigDecimal;

/**
 * Created by zhongbinfan on 2015/9/9.
 */
public class CalculateMathTool {
    // help to calculate big number and heigh float number

    private static final int DEFAULT_DIV_SCALE = 10; // set basic scale for divide
    public static final String ERROR_NUM = "NAN";

    private CalculateMathTool(){
    }

    public static String add(String num1, String num2){
        if(num1.equals(ERROR_NUM) || num2.equals(ERROR_NUM)){
            return ERROR_NUM;
        }
        if(num1.equals(".")){
            num1 = "0";
        }
        if(num2.equals(".")){
            num2 = "0";
        }
        BigDecimal bd1 = new BigDecimal(num1);
        BigDecimal bd2 = new BigDecimal(num2);
        return (bd1.add(bd2)).toString();
    }

    public static String sub(String num1, String num2){
        if(num1.equals(ERROR_NUM) || num2.equals(ERROR_NUM)){
            return ERROR_NUM;
        }
        if(num1.equals(".")){
            num1 = "0";
        }
        if(num2.equals(".")){
            num2 = "0";
        }
        BigDecimal bd1 = new BigDecimal(num1);
        BigDecimal bd2 = new BigDecimal(num2);
        return (bd1.subtract(bd2)).toString();
    }

    public static String mul(String num1, String num2){
        if(num1.equals(ERROR_NUM) || num2.equals(ERROR_NUM)){
            return ERROR_NUM;
        }
        if(num1.equals(".")){
            num1 = "0";
        }
        if(num2.equals(".")){
            num2 = "0";
        }
        BigDecimal bd1 = new BigDecimal(num1);
        BigDecimal bd2 = new BigDecimal(num2);
        return (bd1.multiply(bd2)).toString();
    }

    public static String div(String num1, String num2){
        if(num1.equals(ERROR_NUM) || num2.equals(ERROR_NUM)){
            return ERROR_NUM;
        }
        if(num1.equals(".")){
            num1 = "0";
        }
        if(num2.equals(".")){
            num2 = "0";
        }
        BigDecimal bd1 = new BigDecimal(num1);
        BigDecimal bd2 = new BigDecimal(num2);
        String result;
        try{
            result = (bd1.divide(bd2)).toString(); // Normal divide
        }catch(Exception e){
            try{
                result = (bd1.divide(bd2,  DEFAULT_DIV_SCALE, BigDecimal.ROUND_HALF_UP)).toString(); // Float is too long, so limited
            }catch(Exception ex){
                result = ERROR_NUM; // Divide 0
            }
        }
        return result;
    }
}
