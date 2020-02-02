package com.example.gaming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class Create extends AppCompatActivity {
    TextInputEditText mobile, email, password, cpassword;
    TextInputEditText username;
    Spinner spinner;
   String sex,age,m,patient;
   String Relation="";
    RadioButton self,other;
    EditText pName,relation;
    TextInputLayout  emobile,  epassword, ecpassword;
    PhoneAuthCredential credential;
    String User,Mno,Em,pass,cpass,ename;
    Button createaccount, verify;
    String smsCode, verificationId, s;
    Button conti;
    EditText AGE;
    Boolean isContinue = true;
    String phoneNumber;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        mobile = findViewById(R.id.mobile);
        pName=findViewById(R.id.name);
        self=findViewById(R.id.selfs);
        AGE=findViewById(R.id.Age);
        other=findViewById(R.id.others);
        relation=findViewById(R.id.relation);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);
        emobile = findViewById(R.id.emobile);
        epassword = findViewById(R.id.epassword);
        ecpassword = findViewById(R.id.ecpassword);
        conti = findViewById(R.id.conti);
        firebaseAuth = FirebaseAuth.getInstance();
        spinner=findViewById(R.id.spin);

        if(self.isChecked())
        {
            other.setChecked(false);
            relation.setVisibility(View.GONE);
        }
        self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                other.setChecked(false);
              relation.setVisibility(View.GONE);

            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                self.setChecked(false);
                relation.setVisibility(View.VISIBLE);
            }
        });


     List<String> list=new ArrayList<>();
list.add("Male");
list.add("female");
ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 spinner.setAdapter(arrayAdapter);
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            spinner.setSelection(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            Toast.makeText(Create.this, "Select any one type", Toast.LENGTH_SHORT).show();
        }
    });

        conti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isContinue == true) {
                    String Number = mobile.getText().toString().trim();
                    phoneNumber = "+" + "91" + Number;
try {
    sex = spinner.getSelectedItem().toString();
    age = AGE.getText().toString();
    patient = pName.getText().toString();
    Relation = relation.getText().toString();
    pass = password.getText().toString();
}
catch (Exception e)
{

}

                    Intent i=new Intent(Create.this,OTP_VERi.class);
                    i.putExtra("sex",sex);
                    i.putExtra("phoneNumber",phoneNumber);
                    i.putExtra("age",age);
                    i.putExtra("Relation",Relation);
                    i.putExtra("patient",patient);
                    i.putExtra("mobile",m);
                    i.putExtra("pass",pass);
                    startActivity(i);
                }
            }
        });
    }
    }