package com.example.u.bmi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import java.text.DecimalFormat;
import android.content.DialogInterface;


public class MainActivity extends ActionBarActivity {
    private Button button_cal;
    private EditText num_height;
    private EditText num_weight;
    private TextView show_result;
    private TextView show_suggest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   Button button = (Button)findViewById(R.id.button);
        init();
        button_cal.setOnClickListener(calcbmi);
    }



    private void init(){
        button_cal = (Button)findViewById(R.id.button);
        num_height = (EditText)findViewById(R.id.height);
        num_weight = (EditText)findViewById(R.id.weight);
        show_result = (TextView)findViewById(R.id.result);
        show_suggest = (TextView)findViewById(R.id.suggest);
    }

    private OnClickListener calcbmi = new OnClickListener(){
        public void onClick(View v){

            Intent intent = new Intent();
            intent.setClass(MainActivity.this,Report_activity.class);

            Bundle bun = new Bundle();
            bun.putString("KEY_height",num_height.getText().toString());
            bun.putString("KEY_weight",num_weight.getText().toString());
            intent.putExtras(bun);

            startActivity(intent);

            /*
            DecimalFormat nf = new DecimalFormat("0.00");

            double height = Double.parseDouble(num_height.getText().toString())/100;
            double weight = Double.parseDouble(num_weight.getText().toString());
            double BMI = weight / (height*height);

            show_result.setText("BMI : "+ nf.format(BMI));

            if(BMI > 25){
                show_suggest.setText(R.string.fat);
            }
            else if(BMI < 20){
                show_suggest.setText(R.string.light);
            }
            else{
                show_suggest.setText(R.string.average);
            }*/
        }


    };



    private void openOptionsDialog(){
        AlertDialog.Builder di = new AlertDialog.Builder(MainActivity.this);
        di.setTitle(R.string.dia_title);
        di.setMessage(R.string.dia_content);
        di.setPositiveButton(R.string.ok,new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialogInterface,int i){}
        });
        di.show();
    };


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.menu_main, menu);
    //    android:icon="@android:drawable/ic_menu_help";
   //     menu.add(0,MENU_ABOUT,0,"關於...").setIcon(android.R.drawable.ic_menu_help);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
 /*       if (id == R.id.action_settings) {
            return true;
        }*/
        switch (item.getItemId()){
            case R.id.action_About:
                openOptionsDialog();
                break;
            case R.id.action_Close:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
