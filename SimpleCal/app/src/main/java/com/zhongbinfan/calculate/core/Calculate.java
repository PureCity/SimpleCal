package com.zhongbinfan.calculate.core;

import android.util.Log;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by zhongbinfan on 2015/9/9.
 */
public class Calculate {

    ArrayList<String> dataHeap;

    public Calculate(){
        dataHeap = new ArrayList<>();
    }

    // Design the equation which enter by user
    public String calculate(String equation){

        dataHeap = new ArrayList<String>();

        // Get all data
        char temp;
        String numberTemp = "";
        for (int i = 0; i < equation.length(); i++) {
            temp = equation.charAt(i);
            Log.d("debug - temp :", temp + "");
            switch(temp){
                case '+':
                case '*':
                case '/':
                    if(!numberTemp.equals("")) {
                        dataHeap.add(numberTemp);
                    }
                    dataHeap.add(temp + "");
                    numberTemp = "";
                    break;
                case '-':
                    if(!numberTemp.equals("")) {
                        dataHeap.add(numberTemp);
                    }
                    numberTemp = "" + temp;
                    break;
                default:
                    numberTemp += temp;
                    break;
            }
        }
        dataHeap.add(numberTemp); // fix last number

        for (int i = 0; i < dataHeap.size(); i++){
            Log.d("debug - dataHeap - 1:", dataHeap.get(i).toString());
        }

        //if first is '-'  add 0 before it.
        if (dataHeap.get(0).equals("-")){
            ArrayList<String> tempList = new ArrayList<String>();
            tempList.add("0");
            Log.d("Add le",tempList.get(0).toString());
            for (int i = 0; i < dataHeap.size(); i++){
                tempList.add(dataHeap.get(i));
            }
            dataHeap = tempList;
        }

        for (int i = 0; i < dataHeap.size(); i++){
            Log.d("debug - dataHeap:", dataHeap.get(i).toString());
        }

        // Do calculate
        for(int j = 0; j < dataHeap.size(); j++){
            String tempGet = dataHeap.get(j);
            if (tempGet.equals("*")) {
                String tempResult = CalculateMathTool.mul(dataHeap.get(j - 1), dataHeap.get(j + 1));
                ArrayList<String> tempArray = new ArrayList<String>();
                for(int l = 0; l < dataHeap.size(); l++){
                    if(l == j - 1){
                        tempArray.add(tempResult);
                        l += 2; // leave out from three num
                    }else{
                        tempArray.add(dataHeap.get(l));
                    }
                }
                dataHeap = tempArray;
                j = 0;
            }else if (tempGet.equals("/")) {
                String tempResult = CalculateMathTool.div(dataHeap.get(j - 1), dataHeap.get(j + 1));
                ArrayList<String> tempArray = new ArrayList<String>();
                for(int l = 0; l < dataHeap.size(); l++){
                    if(l == j - 1){
                        tempArray.add(tempResult);
                        l += 2; // leave out from three num
                    }else{
                        tempArray.add(dataHeap.get(l));
                    }
                }
                dataHeap = tempArray;
                j = 0;
            }
        }

        for(int j = 0; j < dataHeap.size(); j++){
            String tempGet = dataHeap.get(j);
            if (tempGet.equals("+") ) {
                String tempResult = CalculateMathTool.add(dataHeap.get(j - 1), dataHeap.get(j + 1));
                ArrayList<String> tempArray = new ArrayList<String>();
                for(int l = 0; l < dataHeap.size(); l++){
                    if(l == j - 1){
                        tempArray.add(tempResult);
                        l += 2; // leave out from three num
                    }else{
                        tempArray.add(dataHeap.get(l));
                    }
                }
                dataHeap = tempArray;
                j = 0;
            }else {
                if (tempGet.charAt(0) == '-'){
                    String tempResult;
                    if(j - 1 < 0){
                        if(j + 1 >= dataHeap.size()){
                            continue;
                        }else {
                            tempResult = CalculateMathTool.add(dataHeap.get(j), dataHeap.get(j + 1));
                            ArrayList<String> tempArray = new ArrayList<String>();
                            for(int l = 0; l < dataHeap.size(); l++){
                                if(l == j){
                                    tempArray.add(tempResult);
                                    l += 1; // leave out from two num
                                }else{
                                    tempArray.add(dataHeap.get(l));
                                }
                            }
                            dataHeap = tempArray;
                            j = 0;
                        }
                    }else {
                        tempResult = CalculateMathTool.add(dataHeap.get(j - 1), dataHeap.get(j));
                        ArrayList<String> tempArray = new ArrayList<String>();
                        for(int l = 0; l < dataHeap.size(); l++){
                            if(l == j - 1){
                                tempArray.add(tempResult);
                                l += 1; // leave out from two num
                            }else{
                                tempArray.add(dataHeap.get(l));
                            }
                        }
                        dataHeap = tempArray;
                        j = 0;
                    }
                }
            }
        }

        if (dataHeap.size() > 1){//fix last
            for (int j = 0; j < dataHeap.size(); j++){
                String tempResult = CalculateMathTool.add(dataHeap.get(j), dataHeap.get(j + 1));
                ArrayList<String> tempArray = new ArrayList<String>();
                for(int l = 0; l < dataHeap.size(); l++){
                    if(l == j){
                        tempArray.add(tempResult);
                        l += 1; // leave out from three num
                    }else{
                        tempArray.add(dataHeap.get(l));
                    }
                }
                dataHeap = tempArray;
                j = 0;
            }
        }

        if(!dataHeap.get(0).equals(CalculateMathTool.ERROR_NUM)) {
            BigDecimal tempdata = new BigDecimal(dataHeap.get(0).toString());
            return tempdata.toString();
        }else{
            return dataHeap.get(0).toString();
        }
    }


}
