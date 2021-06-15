package vsite.hr.lab_11;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;

import androidx.core.app.NotificationCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class timeService extends IntentService {

    public timeService() {
        super("timeService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String time = intent.getStringExtra("time");
            String date = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
            Intent noviIntent = new Intent(this, MainActivity.class);
            noviIntent.putExtra("vrijeme", date);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 12, noviIntent, 0);
            NotificationCompat.Builder graditeljObavijesti = new NotificationCompat.Builder(this, MainActivity.KANAL_ID );
            graditeljObavijesti
                    .setContentTitle("Time servis, prikaz vremena!")
                    .setContentText("vrijeme je prikazano!\n Sve je u redu!")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            Notification obavijest = graditeljObavijesti.build();
            NotificationManager servisManager =  (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            servisManager.notify(1, obavijest);
        }
    }
}