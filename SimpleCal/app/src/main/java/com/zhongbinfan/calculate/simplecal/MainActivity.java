package com.zhongbinfan.calculate.simplecal;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhongbinfan.calculate.core.Calculate;
import com.zhongbinfan.calculate.core.CalculateMathTool;


import java.math.BigDecimal;


public class MainActivity extends Activity {

    //Basic View
    private TextView tvM;
    private TextView tvHistory;
    private TextView tvCurrent;
    private TextView tvbMC;
    private TextView tvbMAdd;
    private TextView tvbMSub;
    private TextView tvbMR;
    private TextView tvbC;
    private TextView tvbDiv;
    private TextView tvbMul;
    private TextView tvbBack;
    private TextView tvb7;
    private TextView tvb8;
    private TextView tvb9;
    private TextView tvbSub;
    private TextView tvb4;
    private TextView tvb5;
    private TextView tvb6;
    private TextView tvbAdd;
    private TextView tvb1;
    private TextView tvb2;
    private TextView tvb3;
    private TextView tvb0;
    private TextView tvbDot;
    private TextView tvbEqual;

    private ScrollView historyScroll;
    private ScrollView currentScroll;

    //Basic Data
    private BigDecimal M;
    private static final String TAG_M = "M";

    boolean isDotOk; // can click a new dot
    boolean shouldClearAnswer;

    private static final int BOTTOM_NORMAL = Color.rgb(33, 13, 1);
    private static final int BOTTOM_DOWN = Color.rgb(190, 123, 85);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);//除去顶部条形
        setContentView(R.layout.activity_main);

        //Set basic data
        M = new BigDecimal("0");
        isDotOk = true;
        shouldClearAnswer = true;

        //Get basic View
        tvM = (TextView) findViewById(R.id.main_tv_m);
        tvHistory = (TextView) findViewById(R.id.tv_main_history);
        tvCurrent = (TextView) findViewById(R.id.tv_main_current);
        tvbMC = (TextView) findViewById(R.id.main_tvb_mc);
        tvbMAdd = (TextView) findViewById(R.id.main_tvb_madd);
        tvbMSub = (TextView) findViewById(R.id.main_tvb_msub);
        tvbMR = (TextView) findViewById(R.id.main_tvb_mr);
        tvbC = (TextView) findViewById(R.id.main_tvb_c);
        tvbDiv = (TextView) findViewById(R.id.main_tvb_div);
        tvbMul = (TextView) findViewById(R.id.main_tvb_mul);
        tvbBack = (TextView) findViewById(R.id.main_tvb_back);
        tvb7 = (TextView) findViewById(R.id.main_tvb_7);
        tvb8 = (TextView) findViewById(R.id.main_tvb_8);
        tvb9 = (TextView) findViewById(R.id.main_tvb_9);
        tvbSub = (TextView) findViewById(R.id.main_tvb_sub);
        tvb4 = (TextView) findViewById(R.id.main_tvb_4);
        tvb5 = (TextView) findViewById(R.id.main_tvb_5);
        tvb6 = (TextView) findViewById(R.id.main_tvb_6);
        tvbAdd = (TextView) findViewById(R.id.main_tvb_add);
        tvb1 = (TextView) findViewById(R.id.main_tvb_1);
        tvb2 = (TextView) findViewById(R.id.main_tvb_2);
        tvb3 = (TextView) findViewById(R.id.main_tvb_3);
        tvb0 = (TextView) findViewById(R.id.main_tvb_0);
        tvbDot = (TextView) findViewById(R.id.main_tvb_dot);
        tvbEqual = (TextView) findViewById(R.id.main_tvb_equal);

        historyScroll = (ScrollView) findViewById(R.id.main_tv_historyscroll);
        currentScroll = (ScrollView) findViewById(R.id.main_currentScroll);

        //Set Listener
        UserSetListener usl = new UserSetListener();
        tvbMC.setOnClickListener(usl);
        tvbMAdd.setOnClickListener(usl);
        tvbMSub.setOnClickListener(usl);
        tvbMR.setOnClickListener(usl);
        tvbC.setOnClickListener(usl);
        tvbDiv.setOnClickListener(usl);
        tvbMul.setOnClickListener(usl);
        tvbBack.setOnClickListener(usl);
        tvb7.setOnClickListener(usl);
        tvb8.setOnClickListener(usl);
        tvb9.setOnClickListener(usl);
        tvbSub.setOnClickListener(usl);
        tvb4.setOnClickListener(usl);
        tvb5.setOnClickListener(usl);
        tvb6.setOnClickListener(usl);
        tvbAdd.setOnClickListener(usl);
        tvb1.setOnClickListener(usl);
        tvb2.setOnClickListener(usl);
        tvb3.setOnClickListener(usl);
        tvb0.setOnClickListener(usl);
        tvbDot.setOnClickListener(usl);
        tvbEqual.setOnClickListener(usl);

        tvbMC.setOnTouchListener(usl);
        tvbMAdd.setOnTouchListener(usl);
        tvbMSub.setOnTouchListener(usl);
        tvbMR.setOnTouchListener(usl);
        tvbC.setOnTouchListener(usl);
        tvbDiv.setOnTouchListener(usl);
        tvbMul.setOnTouchListener(usl);
        tvbBack.setOnTouchListener(usl);
        tvb7.setOnTouchListener(usl);
        tvb8.setOnTouchListener(usl);
        tvb9.setOnTouchListener(usl);
        tvbSub.setOnTouchListener(usl);
        tvb4.setOnTouchListener(usl);
        tvb5.setOnTouchListener(usl);
        tvb6.setOnTouchListener(usl);
        tvbAdd.setOnTouchListener(usl);
        tvb1.setOnTouchListener(usl);
        tvb2.setOnTouchListener(usl);
        tvb3.setOnTouchListener(usl);
        tvb0.setOnTouchListener(usl);
        tvbDot.setOnTouchListener(usl);
        tvbEqual.setOnTouchListener(usl);


        //set tip
        tvHistory.append(Html.fromHtml("Simple Calculate<br/>" +
                "author: Zhongbinfan<br/>" +
                "http://www.zhongbinfan.com<br/>" +
                "jincitykasto@126.com<br/>"));
        historyScroll.fullScroll(ScrollView.FOCUS_DOWN);

    }

    // User OnClick
    private class UserSetListener implements View.OnTouchListener, View.OnClickListener{


        private boolean checkLast(){
            boolean flag = true;
            String temp = tvCurrent.getText().toString();
            if(temp.length() > 0) {
                switch (temp.charAt(temp.length() - 1)) {
                    case '+':
                    case '*':
                    case '/':
                    case '-':
                        flag = false;
                        break;
                    case '.':
                        temp += "0";
                        tvCurrent.setText(temp);
                        break;
                    default:
                        break;
                }
            }
            return flag;
        }

        private boolean checksub(){
            boolean flag = true;
            String temp = tvCurrent.getText().toString();
            if (temp.length() > 0){
                switch (temp.charAt(temp.length() - 1)){
                    case '-':
                        if(temp.charAt(temp.length() - 1) == '-'){ // - - = +
                            char current = temp.charAt(temp.length() - 1);
                            temp = temp.substring(0, temp.length() - 1);
                            tvCurrent.setText(temp);
                            //check dot
                            if(current == '+' || current == '-' || current == '*' || current == '/'){
                                for (int i = temp.length() - 1; i >= 0; i--){
                                    char t = temp.charAt(i);
                                    if(t == '+' || t == '-' || t == '*' || t == '/'){
                                        break;
                                    }
                                    if (t == '.'){
                                        isDotOk = false;
                                    }
                                }
                            }
                            return false;
                        }
                    case '.':
                        temp += "0";
                        tvCurrent.setText(temp);
                        break;
                    default:
                        break;
                }
            }
            return flag;
        }

        private void fixFirst(){
            String temp = tvCurrent.getText().toString();
            if (temp.equals("")){
                tvCurrent.setText("0");
            }
        }

        private void fixLast(){
            String temp = tvCurrent.getText().toString();
            switch (temp.charAt(temp.length() - 1)){
                case '+':
                case '-':
                case '*':
                case '/':
                    temp = temp.substring(0, temp.length() - 1);
                    tvCurrent.setText(temp);
                    break;
                case '.':
                    if(temp.length() == 1){
                        tvCurrent.setText("0");
                        break;
                    }
                    char c = temp.charAt(temp.length() - 2);
                    for(char t = '0'; t <= '9'; t++){
                        if (c == t){
                            temp = temp.substring(0, temp.length() - 1);
                            tvCurrent.setText(temp);
                            break;
                        }
                    }
                    if (c == '+' || c == '-' || c == '*' || c == '/'){
                        temp += "0";
                        tvCurrent.setText(temp);
                        fixLast();
                    }
                    break;
                default:
                    break;
            }
        }

        private void fixClearAnswer(){
            if(shouldClearAnswer){
                tvCurrent.setText("");
                shouldClearAnswer = false;
            }
        }

        private void showAnswer(){
            fixFirst();
            fixLast();
            if(!tvCurrent.getText().toString().equals("")) {
                Calculate cal = new Calculate();
                String result = "";
                try {
                    result = cal.calculate(tvCurrent.getText().toString());
                    tvHistory.append(Html.fromHtml(tvCurrent.getText().toString() + " = " + result + "<br/>"));
                    historyScroll.fullScroll(ScrollView.FOCUS_DOWN);
                    tvCurrent.setText(result);

                }catch(Exception e){
                    result = "ERROR";
                    tvHistory.append(Html.fromHtml(tvCurrent.getText().toString() + " = " + result + "<br/>"));
                    historyScroll.fullScroll(ScrollView.FOCUS_DOWN);
                    tvCurrent.setText(result);
                }
                isDotOk = true;
                shouldClearAnswer = true;
            }
        }

        @Override
        public void onClick(View v) {
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //Change hit
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                if(v.getId() != R.id.main_tvb_equal) {
                    v.setBackgroundColor(BOTTOM_DOWN);
                }
                if (tvCurrent.getText().toString().equals(CalculateMathTool.ERROR_NUM)){
                    tvCurrent.setText("");
                }
            }
            if (event.getAction() == MotionEvent.ACTION_UP){
                switch (v.getId()){
                    case R.id.main_tvb_mc:
                        M = M.subtract(M);
                        tvM.setText("");//clear M
                        break;
                    case R.id.main_tvb_madd:
                        try {
                            if (!tvCurrent.getText().toString().equals("")) {
                                fixFirst();
                                fixLast();
                                Calculate calm = new Calculate();
                                String resultm = calm.calculate(tvCurrent.getText().toString());
                                if (!resultm.equals(CalculateMathTool.ERROR_NUM)) {
                                    String answer = calm.calculate(M.toString() + "+" + resultm);
                                    if (answer != CalculateMathTool.ERROR_NUM) {
                                        M = new BigDecimal(answer);
                                    }
                                }
                            }
                        }catch (Exception e){
                            showAnswer();
                        }
                        break;
                    case R.id.main_tvb_msub:
                        try {
                            if (!tvCurrent.getText().toString().equals("")) {
                                fixFirst();
                                fixLast();
                                Calculate calx = new Calculate();
                                String resultx = calx.calculate(tvCurrent.getText().toString());
                                if (!resultx.equals(CalculateMathTool.ERROR_NUM)) {
                                    String answer = calx.calculate(M.toString() + "-" + resultx);
                                    if (answer != CalculateMathTool.ERROR_NUM) {
                                        M = new BigDecimal(answer);
                                    }
                                }
                            }
                        }catch (Exception e){
                            showAnswer();
                        }
                        break;
                    case R.id.main_tvb_mr:
                        if(tvM.getText().toString().equals(TAG_M)) {
                            tvCurrent.append(M.toString());
                        }
                        break;
                    case R.id.main_tvb_c:
                        tvCurrent.setText("");
                        break;
                    case R.id.main_tvb_div:
                        //fixClearAnswer();
                        fixFirst();
                        if(checkLast()) {
                            tvCurrent.append("/");
                            isDotOk = true;
                            shouldClearAnswer = false;
                        }
                        break;
                    case R.id.main_tvb_mul:
                        //fixClearAnswer();
                        fixFirst();
                        if(checkLast()) {
                            tvCurrent.append("*");
                            isDotOk = true;
                            shouldClearAnswer = false;
                        }
                        break;
                    case R.id.main_tvb_back:
                        fixClearAnswer();
                        String temp = tvCurrent.getText().toString();
                        if(temp.length() >= 1) {
                            char current = temp.charAt(temp.length() - 1);
                            temp = temp.substring(0, temp.length() - 1);
                            tvCurrent.setText(temp);
                            //check dot
                            if (current == '+' || current == '-' || current == '*' || current == '/') {
                                for (int i = temp.length() - 1; i >= 0; i--) {
                                    char t = temp.charAt(i);
                                    if (t == '+' || t == '-' || t == '*' || t == '/') {
                                        break;
                                    }
                                    if (t == '.') {
                                        isDotOk = false;
                                    }
                                }
                            }
                        }
                        break;
                    case R.id.main_tvb_7:
                        fixClearAnswer();
                        tvCurrent.append("7");
                        break;
                    case R.id.main_tvb_8:
                        fixClearAnswer();
                        tvCurrent.append("8");
                        break;
                    case R.id.main_tvb_9:
                        fixClearAnswer();
                        tvCurrent.append("9");
                        break;
                    case R.id.main_tvb_sub:
                        //fixClearAnswer();
                        //fixFirst();
                        if(checksub()) {
                            tvCurrent.append("-");
                            isDotOk = true;
                            shouldClearAnswer = false;
                        }
                        break;
                    case R.id.main_tvb_4:
                        fixClearAnswer();
                        tvCurrent.append("4");
                        break;
                    case R.id.main_tvb_5:
                        fixClearAnswer();
                        tvCurrent.append("5");
                        break;
                    case R.id.main_tvb_6:
                        fixClearAnswer();
                        tvCurrent.append("6");
                        break;
                    case R.id.main_tvb_add:
                        //fixClearAnswer();
                        fixFirst();
                        if(checkLast()) {
                            tvCurrent.append("+");
                            isDotOk = true;
                            shouldClearAnswer = false;
                        }
                        break;
                    case R.id.main_tvb_1:
                        fixClearAnswer();
                        tvCurrent.append("1");
                        break;
                    case R.id.main_tvb_2:
                        fixClearAnswer();
                        tvCurrent.append("2");
                        break;
                    case R.id.main_tvb_3:
                        fixClearAnswer();
                        tvCurrent.append("3");
                        break;
                    case R.id.main_tvb_0:
                        fixClearAnswer();
                        tvCurrent.append("0");
                        break;
                    case R.id.main_tvb_dot:
                        fixClearAnswer();
                        if(isDotOk) {
                            tvCurrent.append(".");
                            isDotOk = false;
                        }
                        break;
                    case R.id.main_tvb_equal:
                        showAnswer();
                        break;
                    default:
                        break;
                }

                //check M
                try{
                    BigDecimal bd = new BigDecimal(M.toString());
                    if (bd.doubleValue() > 0 || bd.doubleValue() < 0){
                        tvM.setText(TAG_M);
                    }else{
                        M = new BigDecimal("0");
                        tvM.setText("");
                    }
                }catch (Exception e){
                    M = new BigDecimal("0");
                    tvM.setText("");
                }

                //Set size
                int len = tvCurrent.getText().toString().length();
                if (len < 15){
                    tvCurrent.setTextSize(50);
                }else if(len >= 15 && len <= 45){
                    tvCurrent.setTextSize(40);
                }else if(len > 45 && len <= 120){
                    tvCurrent.setTextSize(30);
                }else{
                    tvCurrent.setTextSize(20);
                }
                currentScroll.fullScroll(ScrollView.FOCUS_DOWN);
                if(v.getId() != R.id.main_tvb_equal) {
                    v.setBackgroundColor(BOTTOM_NORMAL);
                }
                Log.d("Current:", tvCurrent.getText().toString());
            }

            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
