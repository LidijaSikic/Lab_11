package vsite.hr.lab_11;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static String KANAL_ID = "vrijeme";
    public static String KANAL_IME = "Time servis";
    public static String KANAL_OPIS = "Time servis, prikaz vremena!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();


        String time = getIntent().getStringExtra("time");
        if (time != null) {
            Toast
                    .makeText(this, "Vrijeme je: " + time, Toast.LENGTH_LONG)
                    .show();
        }
    }


    public void onClick(View v){

        int broj = 0;
        while (broj < 5) {
            Intent intent = new Intent(this, timeService.class);
            String date = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
            String date1 = "Vrijeme je : " + date;
            intent.putExtra("time", date1);
            startService(intent);
            String time = getIntent().getStringExtra("vrijeme");
            if (time != null) {
            Toast
                    .makeText(this, "Vrijeme je: " + time, Toast.LENGTH_LONG)
                    .show();
            }
            broj++;
            android.os.SystemClock.sleep(5000);


        }
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(KANAL_ID, KANAL_IME, importance);
            channel.setDescription(KANAL_OPIS);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}