package com.example.gaming;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Completed_frag extends Fragment {
TextView date;
String text="Today is a Vaccination";
String time;
Boolean notification=true;
Boolean exist=false;
String dateTime;
RecyclerView recc;
FirebaseDatabase da;
DatabaseReference databasef;
    public Completed_frag()
    {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view=inflater.inflate(R.layout.fragment_notifications, container, false);
    date=view.findViewById(R.id.date);
da=FirebaseDatabase.getInstance();



         getdate();
  notificationbuild();


       return view;
    }


    public void getdate()

{
    Calendar calender= Calendar.getInstance();
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MMM-yyyy");
     dateTime=simpleDateFormat.format(calender.getTime());
     SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("hh:mm:ss");
     time=simpleDateFormat1.format(calender.getTime());
    Toast.makeText(getContext(), time, Toast.LENGTH_SHORT).show();


}

public void notificationbuild()
{
    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
    {
        NotificationChannel channel=new NotificationChannel("mynotification","mynotification", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager  manager=getActivity().getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);
    }
    NotificationCompat.Builder builder=new NotificationCompat.Builder(getContext(),"mynotification")
            .setContentTitle("Hurry UP!")
            .setSmallIcon(R.drawable.n)
            .setAutoCancel(true)
            .setContentText("Vaccination day");
    NotificationManagerCompat manager=NotificationManagerCompat.from(getContext());
    manager.notify(333,builder.build());

}
}
