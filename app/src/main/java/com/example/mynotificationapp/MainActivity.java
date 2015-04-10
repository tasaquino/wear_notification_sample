package com.example.mynotificationapp;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private static final int NOTIFICATION_ID = 123;
    private static final String EVENT_TITLE = "Exemplo de Notificação!";
    private static final String EVENT_CONTEXT_TEXT = "Esta é uma notificação :)";
    private static final String EXTRA_EVENT_ID = "event_id";

    private Button btnShowNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().getExtras() != null){
            String message = getIntent().getExtras().getString("click");
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }

        btnShowNotification = (Button)findViewById(R.id.button_notification);
        btnShowNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });
    }

    private void showNotification(){
        // Intent for notification
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra("click", "you clicked the notification!");
        PendingIntent mainPendingIntent =
                PendingIntent.getActivity(this, 0, mainIntent, 0);

        // Intent for delete Action
        Intent deleteIntent = new Intent(this, DeleteEventActivity.class);
        PendingIntent deletePendingIntent =
                PendingIntent.getActivity(this, 0, deleteIntent, 0);

        // Intent for reply Action
        Intent replyIntent = new Intent(this, ReplyEventActivity.class);
        PendingIntent replyPendingIntent =
                PendingIntent.getActivity(this, 0, replyIntent, 0);

        // Here you build your notification with the two actions
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.android_icon2)
                        .setContentTitle(EVENT_TITLE)
                        .setContentText(EVENT_CONTEXT_TEXT)
                        .setContentIntent(mainPendingIntent)
                        .addAction(R.drawable.delete,
                            getString(R.string.delete), deletePendingIntent)
                        .addAction(R.drawable.reply,
                                getString(R.string.reply), replyPendingIntent);

        // Instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        // Build the notification and pass to manager
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
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
}
