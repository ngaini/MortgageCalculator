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
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private static SeekBar seekbar_var;
    private static TextView seekbar_text_var;
    private static Button calculate_button_id;
    private static EditText amount_borrowed_id;
    private static CheckBox tax_checkBox_id;
    private static RadioGroup loanTerm_id;
    private static TextView result_id;


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
        seekbar_text_var =(TextView) findViewById(R.id.interestRate_textview);
        seekbar_text_var.setText("Interest rate ");

        seekbar_var.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    private int seekBar_value;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
                    {
//                        seekBar_value =progress;
//                        seekbar_text_var.setText("Interest rate : "+getCoversionIntoFloat(progress));
//                        Toast.makeText(MainActivity.this,"SeekBar in onProgress"+progress+"",Toast.LENGTH_SHORT).show();

                        float value= (float) (progress / 10.0);
                        seekbar_text_var.setText("Interest rate : "+value);
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

    double getCoversionIntoFloat(int value )
    {
        double floatValue = 0.0;
        floatValue = value/10;
        return floatValue;
    }

    public void calculateButton(View v)
    {
       result_id= (TextView) findViewById(R.id.result_textView);
       Log.d("NATE", "Button was pressed !!!");
       amount_borrowed_id = (EditText) findViewById(R.id.amount_borrowed_editText);
       float amount_val = Float.parseFloat(amount_borrowed_id.getText().toString());
        Log.d("NATE", "amount value ="+amount_val);
        result_id.setText(" amount value is :"+amount_val);
    }
}
