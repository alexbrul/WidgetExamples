package com.erenutku.updatingwidgetexample;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;
import java.util.Calendar;


import java.util.Random;

/**
 * Created by yutku on 29/11/16.
 */

public class UpdateService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // generates random number
        String[] slutt = { "ett", "to", "tre", "fire", "fem", "seks", "Syv", "aatte", "ni" , "ti", "elleve", "tolv"};
        Calendar c = Calendar.getInstance();
        int minutter = c.get(Calendar.MINUTE);
        int timer = c.get(Calendar.HOUR);
        String tid = "Klokken er ";

        if(minutter == 0 || minutter == 60){
            ;
        }else if((0<minutter) && (minutter<=5)){
            tid += "fem over";
        }else if(5<minutter && minutter<=10){
            tid += "ti over";
        }else if(10<minutter && minutter<=15){
            tid += "kvart over";
        }else if(15<minutter && minutter<=20){
            tid += "ti på halv";
        }else if(20<minutter && minutter<=25){
            tid += "fem på halv";
        }else if(25<minutter && minutter<=30){
            tid += "halv";
        }else if(30<minutter && minutter<=35){
            tid += "fem over halv";
        }else if(35<minutter && minutter<=40){
            tid += "ti over halv";
        }else if(40<minutter && minutter<=45){
            tid += "kvart på";
        }else if(45<minutter && minutter<=50){
            tid += "ti på";
        }else if(50<minutter && minutter<=55){
            tid += "fem på";
        }else if(55<minutter && minutter<=60){
            ;
        }

        if (minutter>=15){
            timer +=1;
        }
        String lastUpdate = "";
        if(timer<=12) {
            lastUpdate = tid + " " + slutt[timer-1];

        }else{
            int teller = timer%12;
            lastUpdate = tid + " " + slutt[teller-1];
        }



        // Reaches the view on widget and displays the number
        RemoteViews view = new RemoteViews(getPackageName(), R.layout.updating_widget);
        view.setTextViewText(R.id.tvWidget, lastUpdate);
        ComponentName theWidget = new ComponentName(this, UpdatingWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        manager.updateAppWidget(theWidget, view);

        return super.onStartCommand(intent, flags, startId);
    }
}
