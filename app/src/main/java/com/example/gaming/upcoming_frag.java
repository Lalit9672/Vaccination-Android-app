package com.example.gaming;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.gaming.ui.notifications.searchAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class upcoming_frag extends Fragment {
RecyclerView recyclerView;
String intro;
RadioButton vaccineradio;
RadioButton Ageradio;
public searchAd sea;
EditText search;
String ip;
ArrayList<String> age = new ArrayList<>();
DatabaseReference myref;
    FirebaseDatabase database;


    public upcoming_frag()
    {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

       View view= inflater.inflate(R.layout.fragment_upcoming_frag, container, false);
       recyclerView=view.findViewById(R.id.recycleriop);
       search=view.findViewById(R.id.search);
vaccineradio=view.findViewById(R.id.vaccineradio);
Ageradio=view.findViewById(R.id.ageradio);
vaccineradio.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Ageradio.setChecked(false);
        vaccineradio.setChecked(true);
    }
});
Ageradio.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        vaccineradio.setChecked(false);
        Ageradio.setChecked(true);
    }
});

        database = FirebaseDatabase.getInstance();
        myref=database.getReference("vaccine");
        age.add(0,"birth");
        age.add(1,"1 year");
        age.add(2,"2 year");
        age.add(3,"3 year");
        age.add(4,"4 year");
        age.add(5,"5 year");
        age.add(6,"6 year");
        age.add(7,"7 year");
        age.add(8,"8 year");
        age.add(9,"9 year");
        age.add(10,"10 year");

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
     myref.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot dataSnapshot)
         {
for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
{
           ip=dataSnapshot1.child("Patient_Age").getValue().toString();
           if(ip.equals("birth")) {
     intro = dataSnapshot1.child("intro").getValue().toString();
     recyclerView.setAdapter(new searchAd(age,intro,getContext()));
 }
 else if(ip.equals("2 month"))
 {
     intro=dataSnapshot1.child("intro").getValue().toString();
     recyclerView.setAdapter(new searchAd(age,intro,getContext()));
 }
 }



         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }
     });
         search.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable)
    {

        filter(editable.toString());
    }
});


       return view;

    }
private void filter(String text)
{
    ArrayList<String> filteredList=new ArrayList<>();
    for(String iteam: age)
    {
        if(iteam.toLowerCase().contains(text.toLowerCase()))
        {
            filteredList.add(iteam);
        }
    }



        sea.fil(filteredList);


    }
}
