package com.example.android_calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity{
    TextView resultTv, solutionTv;
    Button btnAdd, btnSub, btnMul, btnDiv, btnEqual, btnClear;
    Button btnSin, btnCos, btnTan;
    Button[] numberButtons = new Button[10];
    Button btnDot;
    Switch radianSwitch;

    double valueOne = Double.NaN;
    double valueTwo;
    char CURRENT_ACTION;
    final char ADD = '+', SUBTRACT = '-', MULTIPLY = '*', DIVIDE = '/', NONE = '0';

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        resultTv=findViewById(R.id.result);
        solutionTv=findViewById(R.id.solution);

        for (int i = 0; i < numberButtons.length; i++) {
            String buttonID = "btn" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            numberButtons[i] = findViewById(resID);
        }

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        btnEqual = findViewById(R.id.btnEqual);
        btnClear = findViewById(R.id.btnClear);

        btnSin = findViewById(R.id.btnSin);
        btnCos = findViewById(R.id.btnCos);
        btnTan = findViewById(R.id.btnTan);
        // radianSwitch = findViewById(R.id.switchRadians);

        resultTv = findViewById(R.id.result);

        btnDot = findViewById(R.id.btnDot);


        for (Button btn : numberButtons) {
            btn.setOnClickListener(v -> {
                resultTv.append(btn.getText().toString());
            });
        }

        btnDot.setOnClickListener(v -> resultTv.append("."));

        btnClear.setOnClickListener(v -> clear());

        btnAdd.setOnClickListener(v -> performOperation(ADD));
        btnSub.setOnClickListener(v -> performOperation(SUBTRACT));
        btnMul.setOnClickListener(v -> performOperation(MULTIPLY));
        btnDiv.setOnClickListener(v -> performOperation(DIVIDE));


        btnEqual.setOnClickListener(v -> calculateResult());

        /*btnSin.setOnClickListener(v -> calculateTrigonometricFunction("sin"));
        btnCos.setOnClickListener(v -> calculateTrigonometricFunction("cos"));
        btnTan.setOnClickListener(v -> calculateTrigonometricFunction("tan"));
*/
    }

    private void clear() {
        resultTv.setText("");
        valueOne = Double.NaN;
        valueTwo = Double.NaN;
        CURRENT_ACTION = NONE;
    }

    private void performOperation(char action) {
        if (!Double.isNaN(valueOne)) {
            valueTwo = Double.parseDouble(resultTv.getText().toString());
            resultTv.setText(null);

            switch (CURRENT_ACTION) {
                case ADD:
                    valueOne = valueOne + valueTwo;
                    break;
                case SUBTRACT:
                    valueOne = valueOne - valueTwo;
                    break;
                case MULTIPLY:
                    valueOne = valueOne * valueTwo;
                    break;
                case DIVIDE:
                    valueOne = valueOne / valueTwo;
                    break;
            }
        } else {
            valueOne = Double.parseDouble(resultTv.getText().toString());
        }
        CURRENT_ACTION = action;
        resultTv.setText(null);
    }
    private void calculateResult() {
        if (CURRENT_ACTION != NONE) {
            valueTwo = Double.parseDouble(resultTv.getText().toString());
            switch (CURRENT_ACTION) {
                case ADD:
                    valueOne = valueOne + valueTwo;
                    break;
                case SUBTRACT:
                    valueOne = valueOne - valueTwo;
                    break;
                case MULTIPLY:
                    valueOne = valueOne * valueTwo;
                    break;
                case DIVIDE:
                    valueOne = valueOne / valueTwo;
                    break;
            }
            resultTv.setText(String.valueOf(valueOne));
            CURRENT_ACTION = NONE;
        }
    }

}