package edu.uic.cs478.f2020.madri2.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //GUI widgets implementation
    protected Button activity2; //button to open activity2
    protected Button dialNum; //button to open dial_action

    protected TextView welcome; //welcome text
    protected EditText displayNum; //text that will display the number

    String num; //to store the phoneNumber


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind the interface elements to the corresponding fields
        activity2 = (Button) findViewById(R.id.activity2);
        dialNum = (Button) findViewById(R.id.dialbutton);

        welcome = (TextView) findViewById(R.id.welcometext);
        displayNum = (EditText) findViewById(R.id.showNum);

        // Set up listeners for the buttons
        activity2.setOnClickListener(activity2Listener) ;
        //dialNum.setOnClickListener(dialNumListener);

        Log.i("MainActivity", "Activity completed onCreate method") ;


    }
    // This will be called when the app loses focus; save
    // current state
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //add anything i want to be saved below this line
        setContentView(R.layout.activity_main);

        //create buttons and textFields
        activity2 = (Button) findViewById(R.id.activity2);
        dialNum = (Button) findViewById(R.id.dialbutton);
        welcome = (TextView) findViewById(R.id.welcometext);
        displayNum = (EditText) findViewById(R.id.showNum);

        // Set up listeners for the buttons
        activity2.setOnClickListener(activity2Listener) ;


    }
    //once button is pressed the SecondActivity will launch
    public View.OnClickListener activity2Listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           // switchToActivityTwo();
            Intent i = new Intent(MainActivity.this,SecondActivity.class);
            startActivityForResult(i,1);
        }
    };

//    private void switchToActivityTwo(){
//        Intent i = new Intent(MainActivity.this,SecondActivity.class);
//        startActivityForResult(i,1);
//    }

    //Logic for when the phone number gets back to the MainActivity
    protected void onActivityResult(int code, int result_code, Intent i) {

        super.onActivityResult(code, result_code, i);
        Log.i("MainActivity: ", "Returned result is: " + result_code);
        num = i.getExtras().getString("phoneNumber");


        if(result_code == RESULT_OK){
            if (i.hasExtra("phoneNumber")) {
                displayNum.setText(num);
                dialNum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+num));
                        startActivity(callIntent);
                    }
                });
            }
        }
        else if(result_code == RESULT_CANCELED){
            if (i.hasExtra("phoneNumber")) {
                displayNum.setText(num);
                dialNum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "The following number is invalid: " + num , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        //add the result (phonenumber) into the editText box
        //if result returned is a proper number then add listener for dial button to open phone_app --> ACTION_DIAL
        //

        //if result returned is not a proper number then add listener for dial button to display a toast that says "improper number"
        ////Toast.makeText(this, i.getExtras().getString("phoneNumber")).show();

    }





















}