package com.blahblahblah.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculatoor extends Activity {
    private EditText firstNumber = null;
    private EditText secondNumber = null;
    private Button calcButton = null;
    private TextView result = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        firstNumber = (EditText) findViewById(R.id.first_number);
        secondNumber = (EditText) findViewById(R.id.second_number);
        calcButton = (Button) findViewById(R.id.calculate);
        result = (TextView) findViewById(R.id.answer);
        
        calcButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				int a = Integer.parseInt(firstNumber.getText().toString());
				int b = Integer.parseInt(secondNumber.getText().toString());
				
				Integer res = a + b;
				result.setText(res.toString());
			}
        	
        });
        
        
    }
    
}