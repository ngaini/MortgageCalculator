package com.ngaini.calculatorapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private static SeekBar seekbar_var;
    private static TextView seekbar_value_var;
    private static Button calculate_button_id;
    private static EditText amount_borrowed_id;
    private static CheckBox tax_checkBox_id;
    private static RadioGroup loanTerm_id;
    private static TextView result_id;
    private static RadioButton radioButton_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekbarMethod();

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

    // logic for the seekbar
    private void seekbarMethod()
    {
        seekbar_var = (SeekBar) findViewById(R.id.interest_rate_seekbar);
        seekbar_value_var =(TextView) findViewById(R.id.interestRate_value_text);
        seekbar_value_var.setText(" "+ getCoversionIntoFloat(seekbar_var.getProgress())+"/10.0");


        seekbar_var.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    private int seekBar_value;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
                    {
//                        seekBar_value =progress;
//                        seekbar_value_var.setText("Interest rate : "+getCoversionIntoFloat(progress));
//                        Toast.makeText(MainActivity.this,"SeekBar in onProgress"+progress+"",Toast.LENGTH_SHORT).show();

                        float value= (float) (progress / 10.0);
                        seekbar_value_var.setText(" " + value+"/10.0");
//                        Toast.makeText(MainActivity.this,"SeekBar in onProgress "+progress+"",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar)
                    {
//                        Toast.makeText(MainActivity.this,"SeekBar in startTracking",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar)
                    {
//                        Toast.makeText(MainActivity.this,"SeekBar in stopTracking",Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    double getCoversionIntoFloat(float value )
    {
        double floatValue = 0.0;
        floatValue = value/10;
        return floatValue;
    }

    public void calculateButton(View v)
    {
       // Fetch interest rate values
        float interest_rate_val=(float)getCoversionIntoFloat(seekbar_var.getProgress());

       result_id= (TextView) findViewById(R.id.result_textView);
       Log.d("NATE", "Button was pressed !!!");
       //Fetch amount borrowed value
       amount_borrowed_id = (EditText) findViewById(R.id.amount_borrowed_editText);
       float amount_val = Float.parseFloat(amount_borrowed_id.getText().toString());
        Log.d("NATE", "amount value ="+amount_val);

        // fetch value from radioGroup
        loanTerm_id = (RadioGroup) findViewById(R.id.term_radioGroup);
        int selected_id= loanTerm_id.getCheckedRadioButtonId();
        radioButton_id =(RadioButton) findViewById(selected_id);
        int loanTerm_value= Integer.parseInt(radioButton_id.getText().toString());
        float taxValue = getCheckBoxValue();
        float monthly_payment_amount = calculateMonthlyPayment(interest_rate_val,amount_val,loanTerm_value,taxValue);
        // Print values on the display area
//        result_id.setText(" amount value is :"+amount_val+" IR val :"+interest_rate_val+" ::"+loanTerm_value+"::"+taxValue+"::"+monthly_payment_amount);
        result_id.setText(" Monthly Payment Value is $"+monthly_payment_amount);
    }
    public float getCheckBoxValue()
    {
        float temp= 0;
        tax_checkBox_id = (CheckBox) findViewById(R.id.tax_checkBox);
        if(tax_checkBox_id.isChecked())
        {
            temp =(float) 100.0;
        }
        return temp;


    }

    public float calculateMonthlyPayment(float interest_rate_val, float amount_borrowed, int loan_term, float tax_value)
    {

        float monthlyPayment_val;
        if(interest_rate_val==0)
        {
            monthlyPayment_val = (float)((amount_borrowed/loan_term)+tax_value);
        }
        else
        {
            double part1_value = Math.pow(1+interest_rate_val,-loan_term);
            double part2_value= (amount_borrowed*(interest_rate_val/(1-part1_value)));
            monthlyPayment_val=(float)  part2_value+tax_value;
        }
        return monthlyPayment_val;
    }
}
