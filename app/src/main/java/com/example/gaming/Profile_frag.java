package com.example.gaming;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gaming.ui.MottoAdapter;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile_frag extends Fragment {
String Pa_age;
List<String> list=new ArrayList<>();
RecyclerView recycler;
String patient,age;
Adapter adapter;
Boolean exit=false;
TextView Name,Age;
DatabaseReference myref,my;

FirebaseDatabase database;
    public Profile_frag()
    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view= inflater.inflate(R.layout.fragment_profile_frag, container, false);
        database = FirebaseDatabase.getInstance();
       Name=view.findViewById(R.id.name);
       Age=view.findViewById(R.id.Age);
       list.removeAll(list);
       Age.setText("");
        final Bundle bundle=getActivity().getIntent().getExtras();
       patient=bundle.getString("patient");
        recycler=view.findViewById(R.id.recyclerview);
 recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        my=database.getReference("vaccine");
        myref = database.getReference("Profile_data");
        myref.addValueEventListener(new ValueEventListener() {
       @Override

       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
         Boolean exist=false;   String userna;
           for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
           {
          userna=dataSnapshot1.child("name").getValue().toString();
       if(userna.equals(patient))
       {

         age=dataSnapshot1.child("umar").getValue().toString();
           Toast.makeText(getContext(), age, Toast.LENGTH_SHORT).show();
         exist=true;
       }
           }
           if(exist==true)
           {
               Age.setText(age);
               Name.setText(patient);
           }
       }

       @Override
       public void onCancelled(@NonNull DatabaseError databaseError) {

       }
   });
 getdata();

       return view;
    }
public void getdata()
{
my.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
        {

            String a=Age.getText().toString();
           Pa_age=dataSnapshot1.child("Patient_Age").getValue().toString();
if(a.equals(Pa_age))
{
    if(a.equals("birth"))
    {
        exit=true;
        String intro;  String ni;
        intro=dataSnapshot1.child("intro").getValue().toString();
       ni= intro.substring(0,11);
       list.add(ni);
       recycler.setAdapter(new MottoAdapter(list,intro,getContext()));
    }
    else if(a.equals("12 year"))
    {
        exit=true;
        String s;  String nio; String io;
        s=dataSnapshot1.child("intro").getValue().toString();
       nio= s.substring(0,13);
       io=s.substring(15,28);
       list.add(nio);
       list.add(io);
       recycler.setAdapter(new MottoAdapter(list,s,getContext()));
    }
    else if(a.equals("18 year"))
    {
        exit=true;
        String s;  String nio; String io;
        s=dataSnapshot1.child("intro").getValue().toString();
        nio= s.substring(0,13);
        io=s.substring(14,28);
        list.add(nio);
        list.add(io);
        recycler.setAdapter(new MottoAdapter(list,s,getContext()));

    }
}


      }
        if(exit==false)
        {
            Toast.makeText(getContext(), "No Data found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});
}
}
