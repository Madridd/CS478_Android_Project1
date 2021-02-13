package edu.uic.cs478.f2020.madri2.project1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    //fields for the GUI widgets
    private TextView activity2text; //the Enter a phone number text
    private EditText phonetext; //where user will enter a phone number

    public void onCreate(Bundle savedInstancesState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_two);

        // Bind the interface elements to the corresponding fields
        activity2text = (TextView) findViewById(R.id.activity2Textview);
        phonetext = (EditText) findViewById(R.id.addNumberText) ;

        //Logic for when a number is entered, immedialty go back to the mainActivity with a RESULT_OK or RESULT_CANCELED
        phonetext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if(i == EditorInfo.IME_ACTION_DONE){
                    String phone = phonetext.getText().toString();
                    Intent i2 = new Intent();
                    i2.putExtra("phoneNumber",phone);

                    //allows phone number to have () . - or single spaces and expects 10numbers
                    if(phone.matches("\\(?\\d{3}\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}")){
                        setResult(Activity.RESULT_OK,i2);
                        finish();
                    }
                    else{
                        setResult(Activity.RESULT_CANCELED,i2);
                        finish();
                    }

                }

                //add setResult(RESULT_OK) below if what was entered is a phone number
                //case KeyEvent.KEYCODE_ENTER: --> to return to mainActivity
                //finish();

                //if not a valid number setResult(RESULT_CANCELED)
                //case KeyEvent.KEYCODE_ENTER: --> to return to mainActivity
                //finish();

                return false;
            }
        });




    }

    protected void onPause() {
        super.onPause() ;
        // finish() ;
    }




}//end of class
