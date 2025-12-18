package com.example.calcidemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText etDisplay;
    double num1 = 0, num2 = 0;
    String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        etDisplay = findViewById(R.id.etDisplay);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void onNumberClick(View view)
    {
        Button button=(Button) view;
        etDisplay.append(button.getText().toString());
    }
    public void onOperatorClick(View view) {
        Button button = (Button) view;
        operator = button.getText().toString();

        if (!etDisplay.getText().toString().isEmpty()) {
            num1 = Double.parseDouble(etDisplay.getText().toString());
            etDisplay.setText("");
        }
    }
    public void onEqualClick(View view) {
        if (!etDisplay.getText().toString().isEmpty()) {
            num2 = Double.parseDouble(etDisplay.getText().toString());
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "×":
                    result = num1 * num2;
                    break;
                case "÷":
                    if (num2 != 0)
                        result = num1 / num2;
                    else {
                        etDisplay.setText("Error");
                        return;
                    }
                    break;
            }

            etDisplay.setText(String.valueOf(result));
        }
    }

    // Clear button click
    public void onClearClick(View view) {
        etDisplay.setText("");
        num1 = num2 = 0;
        operator = "";
    }
}
