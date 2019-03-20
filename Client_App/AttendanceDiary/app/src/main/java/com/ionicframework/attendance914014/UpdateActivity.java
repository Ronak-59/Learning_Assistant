package com.ionicframework.attendance914014;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class UpdateActivity extends AppCompatActivity {

    /*
    todo schedule notification
    todo add pending intent
     */

    public final static String notifications = "notify";
    public final static String usedToday = "Used Today";
    public final static String first = "first";

    private Toolbar _toolbar;
    private Subject _subject;
    private TextView _present, _absent, _lastSeen, _info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        _toolbar = (Toolbar) findViewById(R.id.action_update);

        _subject = SubjectLoader.get(this).getSubject(getIntent().getLongExtra("Sub", 0));

        _toolbar.setTitle(_subject.getName());

        _info = (TextView) findViewById(R.id.info_bunk_attend);

        _lastSeen = (TextView) findViewById(R.id.info_last_updated);


        //Setting up cards
        keepInSync();


        findViewById(R.id.present_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                present(view);
            }
        });

        findViewById(R.id.absent_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                absent(view);
            }
        });

        findViewById(R.id.undo_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Integer> undoStack = _subject.getUndoStack();

                if (!undoStack.isEmpty()) {
                    int currentAction = undoStack.remove(undoStack.size() - 1);
                    if (currentAction == 11) {
                        undoPresent(view);
                    } else if (currentAction == 10) {
                        undoAbsent(view);
                    }
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update, menu);
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

    private void keepInSync() {
        //getAlarmTime();
        //showList(_subject.getUndoStack());
        _present = (TextView) findViewById(R.id.present_view);
        _absent = (TextView) findViewById(R.id.total_view);

        _present.setText("Present " + _subject.getPresentClass());
        _absent.setText("Total " + _subject.getTotalClass());




       /* NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher).setContentTitle("Shortage  Attention in " + _subject.getName());*/
        NotificationManager managerCompat = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        int reqClass = getRequiredClass(_subject.getPresentClass(), _subject.getTotalClass());
        if (reqClass > 0) {
            // builder.setContentText("You have to attend " + reqClass + " classes ");
            scheduleNotification(getNotification("You have to attend " + reqClass + " classes", "Shortage in " + _subject.getName()), (int) _subject.getId(), 5000, false);
        } else {
            // builder.setContentText("You are going just right");
            //managerCompat.cancel((int) _subject.getId());
            //Log.d("Debugging notifications","The id is " + _subject.getId());
            scheduleNotification(getNotification("All iz well", "In " + _subject.getName()), (int) _subject.getId(), 5000, true);

        }


      /*  if (reqClass > 0 ) {
            managerCompat.notify((int) _subject.getId(),builder.build());
        } else {
            managerCompat.cancel((int) _subject.getId());
        }
*/

        if (reqClass > 0) {
            _info.setText("Attend " + reqClass + " more to reach your objective");
        } else if (reqClass == -760) {
            _info.setText("You'll have to do a time travel");
        } else if (reqClass < 0) {
            reqClass = -reqClass;
            _info.setText("Congrats! you can bunk " + reqClass + " !!!!!");
        } else {
            _info.setText("You are going just fine");
        }

        if (_subject.getLastUpdated() != null) {
            _lastSeen.setText("Last Updated on " + _subject.getLastUpdated());
        } else {
            _lastSeen.setText(" Let's get started ");
        }


    }

    public int getRequiredClass(int present, int total) {
        int initialPresent = present;
        int initialTotal = total;
        int flag = 0;

        SharedPreferences settings = getSharedPreferences(SettingsFragment.requiredPercentage, 0);
        int reqPercentage = settings.getInt("percentage", 75);

        if (total == 0) {
            return 0;
        }


        if ((present * 100 / total) < reqPercentage) {

            if (reqPercentage == 100) {
                return -760;
            }

            while ((present * 100 / total) < reqPercentage) {
                present++;
                total++;
                flag = 1;
            }
        } else if ((present * 100 / total) > reqPercentage) {

            while ((present * 100 / total) > reqPercentage) {
                total++;
                flag = -1;
            }
        }

        if (flag > 0) {
            return present - initialPresent;
        } else if (flag < 0) {
            return (initialTotal - (--total));
        }

        return 0;


    }

    public void absent(View view) {
        _subject.setTotalClass(_subject.getTotalClass() + 1);
/*
        Calendar c = Calendar.getInstance();
        _subject.setLastUpdated(c.getTime().toString());*/

        Calendar mReminderCal = Calendar.getInstance();
        mReminderCal.setTimeInMillis(System.currentTimeMillis());
        Date dateText = new Date(mReminderCal.get(Calendar.YEAR) - 1900,
                mReminderCal.get(Calendar.MONTH),
                mReminderCal.get(Calendar.DAY_OF_MONTH),
                mReminderCal.get(Calendar.HOUR_OF_DAY),
                mReminderCal.get(Calendar.MINUTE));
        String dateString = android.text.format.DateFormat.format("MM/dd/yyyy hh:mm", dateText).toString();


        _subject.setLastUpdated(dateString);

        new DatabaseHandler(view.getContext()).updateSubject(_subject);

        _subject.getUndoStack().add(10);
        keepInSync();
    }

    public void present(View view) {
        _subject.setPresentClass(_subject.getPresentClass() + 1);
        _subject.setTotalClass(_subject.getTotalClass() + 1);


        Calendar mReminderCal = Calendar.getInstance();
        mReminderCal.setTimeInMillis(System.currentTimeMillis());
        Date dateText = new Date(mReminderCal.get(Calendar.YEAR) - 1900,
                mReminderCal.get(Calendar.MONTH),
                mReminderCal.get(Calendar.DAY_OF_MONTH),
                mReminderCal.get(Calendar.HOUR_OF_DAY),
                mReminderCal.get(Calendar.MINUTE));
        String dateString = android.text.format.DateFormat.format("MM/dd/yyyy hh:mm", dateText).toString();


        _subject.setLastUpdated(dateString);

        new DatabaseHandler(view.getContext()).updateSubject(_subject);

        _subject.getUndoStack().add(11);

        keepInSync();
    }

    public void undoPresent(View view) {
        _subject.setPresentClass(_subject.getPresentClass() - 1);
        _subject.setTotalClass(_subject.getTotalClass() - 1);

       /* Calendar c = Calendar.getInstance();


        SimpleDateFormat date = new SimpleDateFormat("MMMMM dd, yyyy HH:mm a");
        String d = date.format(c);

        _subject.setLastUpdated(d);*/

        new DatabaseHandler(view.getContext()).updateSubject(_subject);


        keepInSync();
    }

    public void undoAbsent(View view) {
        _subject.setTotalClass(_subject.getTotalClass() - 1);
        new DatabaseHandler(view.getContext()).updateSubject(_subject);
        keepInSync();
    }

    public void showList(ArrayList<Integer> list) {
        int i = 0;
        while (i < list.size() - 1) {
            Log.d("List", "Element " + i + " is " + list.get(i++));
        }
    }

    private void scheduleNotification(Notification notification, int id, int delay, boolean cancel) {

        Intent notificationIntent = new Intent(this, NotificationReceiver.class);
        notificationIntent.putExtra(NotificationReceiver.NOTIFICATION_ID, id);
        notificationIntent.putExtra(NotificationReceiver.NOTIFICATION, notification);

        notificationIntent.putExtra(NotificationReceiver.NOTIFICATION_CANCEL, cancel);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, (int) _subject.getId(), notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        long alarm = getAlarmTime();
        if (alarm != -1) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, getAlarmTime(),86400000, pendingIntent);
            notUpdatedTodaySetup(true);
        } else {
            notUpdatedTodaySetup(false);
        }

        // alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(getAlarmTime(),pendingIntent),pendingIntent);

        SharedPreferences settings = getSharedPreferences(notifications,0);
        boolean firstUse = settings.getBoolean(first,true);

        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(usedToday,true);


     //   Log.e(NotificationReceiver.TAG,"First use is " + firstUse);

      //  if (firstUse) {

          //  editor.putBoolean(first,false);
         //   editor.commit();
       // }

      //  editor.commit();
    }

    private Notification getNotification(String content, String title) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_launcher);
        return builder.build();
    }

    public long getAlarmTime() {
        Calendar c1 = Calendar.getInstance();
        Calendar c = Calendar.getInstance();

        if (c.get(Calendar.HOUR_OF_DAY) < 20) {

            c1.set(Calendar.HOUR_OF_DAY, 20);

        } else {


            int nextDay = c.get(Calendar.DAY_OF_WEEK) + 1;

            if ((nextDay - 1) == 7) {
                return -1;
            }

            c1.set(Calendar.DAY_OF_WEEK, nextDay);//todo set it correctly
            c1.set(Calendar.HOUR_OF_DAY, 20);
        }

        Log.e("time", "day of week is " + c1.get(Calendar.DAY_OF_WEEK) + " day of month " + c1.get(Calendar.HOUR_OF_DAY) + " date " + Calendar.DATE);

        return c1.getTimeInMillis();
    }

    public void notUpdatedTodaySetup(boolean cancel) {

        Calendar c = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();

        int nextDay = c.get(Calendar.DAY_OF_WEEK) + 1;

        if ((nextDay - 1) == 7) {
            nextDay = 1;
        }

        c1.set(Calendar.DAY_OF_WEEK, nextDay);//todo set it correctly
        c1.set(Calendar.HOUR_OF_DAY, 20);


        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.putExtra(NotificationReceiver.NOTIFICATION_ID, 810);
        intent.putExtra(NotificationReceiver.NOTIFICATION, getNotification("You have not updated today's attendance","Attention"));

        intent.putExtra(NotificationReceiver.NOTIFICATION_CANCEL, false);

        PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //Log.e(NotificationReceiver.TAG,"Here cancel: "+cancel);
        if (cancel) {

            //alarmManager.set(AlarmManager.RTC_WAKEUP, getAlarmTime(), pendingIntent);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c1.getTimeInMillis(),86400000,broadcast);

        } else {
       //     Log.e(NotificationReceiver.TAG,"Here");
            alarmManager.cancel(broadcast);
        }


      //  Log.e(NotificationReceiver.TAG,"Setting up not updated today");
    }
}
