package com.example.gaming;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
EditText Username;
Button Login;
String pass,user,passs;
List<POJO> list=new ArrayList<>();
   Boolean exist=false;
ProgressDialog progressDialog;
FirebaseDatabase database;
DatabaseReference myref;
TextInputEditText password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         password = findViewById(R.id.pw);
         Login=findViewById(R.id.login);
         Username=findViewById(R.id.username);
         database = FirebaseDatabase.getInstance();
         myref = database.getReference("Profile_data");
          progressDialog=new ProgressDialog(this);
progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
progressDialog.setMessage("Verifying Please Wait");
progressDialog.setCancelable(false);
        Login.setOnClickListener(new View.OnClickListener() {
             @Override

             public void onClick(View view) {
exist=false;
            progressDialog.show();
                 read();

             }
         });
    }

    public void create(View view)
    {

        Intent i=new Intent(MainActivity.this,Create.class);
        startActivity(i);
    }

public void read()
{
    myref.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            user = Username.getText().toString();
            String usern="";

    pass = password.getText().toString();




            for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
            {
                   usern=dataSnapshot1.child("name").getValue().toString();
                if(usern.equals(user))
                {
      passs=dataSnapshot1.child("password").getValue().toString();
                    exist=true;

                }
            }
            if(exist==true)
            {
                if(passs.equals(pass))
                {
                        Intent i=new Intent(MainActivity.this,Home_Bottom.class);
                        i.putExtra("patient",user);
                        startActivity(i);
                        progressDialog.cancel();


                }
                else
                {
                   progressDialog.cancel();
                   Toast.makeText(MainActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
             progressDialog.cancel();
                Toast.makeText(MainActivity.this, "Invalid Username or Yet Not Registered", Toast.LENGTH_SHORT).show();
            }

        }














        @Override
        public void onCancelled(DatabaseError databaseError) {
            System.out.println("The read failed: " + databaseError.getCode());
        }
    });
}
}
