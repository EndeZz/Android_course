package com.gmail.etozhesergius.android_course;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import com.gmail.etozhesergius.android_course.ContactsService.MyBinder;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements ServiceInterface {
    final String LOG_TAG = "myLogs";
    private ContactsService cService;
    private ServiceConnection sConnection;
    private boolean sBound = false;
    private boolean startNewApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Intent, который позволит нам добраться до сервиса.
        Intent intent = new Intent(MainActivity.this, ContactsService.class);
        startNewApp = savedInstanceState == null;
        //Объект ServiceConnection позволит нам определить, когда мы подключились к сервису и когда связь с сервисом потеряна
        sConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyBinder binder = (MyBinder) service;
                cService = binder.getService();
                sBound = true;
                Log.d(LOG_TAG, "Сервис подключен onServiceConnected()");
                if (startNewApp) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    ContactListFragment fragment = new ContactListFragment();
                    fragmentTransaction.add(R.id.fragment_list, fragment);
                    fragmentTransaction.commit();
                }
            }

            @Override
            //При потере связи сработает метод onServiceDisconnected()
            public void onServiceDisconnected(ComponentName name) {
                Log.d(LOG_TAG, "Сервис отключен ");
                sBound = false;
                cService = null;
            }
        };
        bindService(intent, sConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public ContactsService getService() {
        Log.d(LOG_TAG, "Данные контактов получены ");
        return cService;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(sBound){
            Log.d(LOG_TAG, "Сервис уничтожен");
            unbindService(sConnection);
            sBound = false;
        }
    }
}

