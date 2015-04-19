package com.example.u.bmi;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;


public class Report_activity extends ActionBarActivity {

    private Button button_back;
    private TextView result;
    private TextView suggest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_activity);

        view();
        Results();
        setListeners();
    }

    private void view(){
        button_back = (Button) findViewById(R.id.button2);
        result = (TextView) findViewById(R.id.result2);
        suggest = (TextView) findViewById(R.id.suggest2);
    }

    private void Results(){
        DecimalFormat nf = new DecimalFormat("0.00");
        Bundle bun = this.getIntent().getExtras();
        double height = Double.parseDouble(bun.getString("KEY_height"))/100;
        double weight = Double.parseDouble(bun.getString("KEY_weight"));
        double BMI = weight / (height*height);

        result.setText(getString(R.string.bmi_result)+ nf.format(BMI));

        if(BMI > 25){
            suggest.setText(R.string.fat);
        }
        else if(BMI < 20){
            showNotification(BMI);
            suggest.setText(R.string.light);
        }
        else{
            suggest.setText(R.string.average);
        }
    }

    private void setListeners(){
        button_back.setOnClickListener(backtoMain);
    }

    private Button.OnClickListener backtoMain = new Button.OnClickListener(){
        public void onClick(View v){
            Report_activity.this.finish();
        }
    };

    protected void showNotification(double BMI){
        NotificationManager barmess = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
   //     PendingIntent contentIntent = PendingIntent.getActivitiy(this,0,null,PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent contentIntent = PendingIntent.getActivity(
                 this, 0, new Intent(this , MainActivity. class),PendingIntent. FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(this)
                .setTicker("too thin").setContentTitle("jane").setContentText("abc").setSmallIcon(R.drawable.ic_launcher).setContentIntent(contentIntent);
 /*       PendingIntent contentIndent = PendingIntent.getActivity(
                Report_activity. this, 0, new Intent(Report_activity.this,  MainActivity. class),
                PendingIntent. FLAG_UPDATE_CURRENT);*/

        Notification notification = builder.getNotification();
        barmess.notify(0, notification);
 //       Notification notification = builder.getNotification();

//        Notification barmes = new Notification(R.drawable.ic_launcher,"too thin !! jane~~",System.currentTimeMillis());
 //       PendingIntent contentintent = PendingIntent.getActivities(this,0,null,PendingIntent.FLAG_UPDATE_CURRENT);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_report_activity, menu);
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
