package com.benrabahmohamed.android.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {

    Button btn0, btn1, btn2, btn3 , btn4, btn5, btn6, btn7, btn8, btn9,
            btnAddOp, btnSub, btnMul, btnDiv, btnMod,
            btnDel, btnSigne, btnEqual, btnDot, btnClear;
    TextView tvInput, tvOutput;
    String process;
    boolean checkbracket = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //number
        btn0 = findViewById(R.id.zero);
        btn1 = findViewById(R.id.one);
        btn2 = findViewById(R.id.two);
        btn3 = findViewById(R.id.three);
        btn4 = findViewById(R.id.four);
        btn5 = findViewById(R.id.five);
        btn6 = findViewById(R.id.six);
        btn7 = findViewById(R.id.seven);
        btn8 = findViewById(R.id.eight);
        btn9 = findViewById(R.id.nine);
        //operation
        btnAddOp = findViewById(R.id.addOp);
        btnSub = findViewById(R.id.sub);
        btnDiv = findViewById(R.id.div);
        btnMul = findViewById(R.id.mult);
        btnMod = findViewById(R.id.mod);
        //other
        btnClear = findViewById(R.id.clear);
        btnSigne = findViewById(R.id.sign);
        btnEqual = findViewById(R.id.equal);
        btnDel = findViewById(R.id.del);
        btnDot = findViewById(R.id.dot);
        //input and output
        tvInput = findViewById(R.id.input);
        tvOutput = findViewById(R.id.output);
        //intialisation
        tvInput.setText("");
        tvOutput.setText("0");

        //button action
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = "";
                tvInput.setText(process);
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                if(process.length()>0)
                    process = process.substring(0,process.length()-1);
                tvInput.setText(process);
            }
        });

        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputTxt("%");
            }
        });
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                if(!process.contains("."))
                    inputTxt(".");
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputTxt("/");
            }
        });
        btnAddOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputTxt("+");
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputTxt("-");
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputTxt("x");
            }
        });

        btnSigne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkbracket) {
                    inputTxt(")");
                    checkbracket = false;
                }
                else {
                    inputTxt("(-");
                    checkbracket = true;
                }
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputTxt("0");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputTxt("1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputTxt("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputTxt("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputTxt("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputTxt("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputTxt("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputTxt("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputTxt("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputTxt("9");
            }
        });

        //faire les calcul
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                process = process.replaceAll("x","*");

                Context rhino = Context.enter();
                rhino.setOptimizationLevel(-1);
                String finalRes = "";

                try{
                    Scriptable sc = rhino.initStandardObjects();
                    finalRes = rhino.evaluateString(sc, process, "javascript",1,null).toString();
                }
                catch (Exception e){
                    finalRes = "";
                }
                tvOutput.setText(finalRes);
            }
        });
    }

    // make change on input field
    private void inputTxt(String str){
        process = tvInput.getText().toString();
        tvInput.setText(process.concat(str));
    }
}