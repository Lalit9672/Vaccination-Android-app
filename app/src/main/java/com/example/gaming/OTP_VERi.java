package com.example.gaming;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.goodiebag.pinview.Pinview;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OTP_VERi extends AppCompatActivity {
Pinview otp; TextView textView,number;
String id;
String f,se,th;
List<POJO> listdata=new ArrayList<>();
    private DatabaseReference mDatabase;
    String phoneNumber;
Button verify;
FirebaseAuth firebaseAuth;
PhoneAuthCredential credential;
    String  verificationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp__veri);
        otp=findViewById(R.id.otp);
        verify=findViewById(R.id.verify);
        number=findViewById(R.id.number);
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Profile_data");
        textView=findViewById(R.id.text);




        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        Handler handler=new Handler();
//read.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String mob=" ";
//                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
//                {
//                    String s="hello";  Boolean avail=false;
//                    String username=dataSnapshot1.child("username").getValue().toString();
//                    if(s.equals(username))
//                    {
//                      mob=dataSnapshot1.child("mobile").getValue().toString();
//                    avail=true;
//                    }
//                    else
//                    {
//                        avail=false;
//                    }
//                }
//
//                Toast.makeText(OTP_VERi.this, mob, Toast.LENGTH_SHORT).show();
//
//            }

//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Toast.makeText(OTP_VERi.this, "Failed to read", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//});
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("Enter the OTP sent to");
            }
        },10000);
        Bundle bundle=getIntent().getExtras();
        phoneNumber=bundle.getString("phoneNumber");
        number.setText(phoneNumber);
        sendVerificationCode(phoneNumber);
        verify.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view)
    {
        progressDialog.show();
        if(otp.getValue().trim().length()==6)
        {
            enableUserManuallyInputCode();
            progressDialog.cancel();
        }
        else
        {
            Toast.makeText(OTP_VERi.this, "Enter valid OTP", Toast.LENGTH_SHORT).show();
        progressDialog.cancel();
        }
    }
});

    }

    public void sendVerificationCode(String number) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number, 60, TimeUnit.SECONDS, TaskExecutors.MAIN_THREAD, mCallBack

        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                otp.setValue(code);
                verifyCode(code);

            }


        }

        @Override
        public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
            super.onCodeAutoRetrievalTimeOut(s);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(OTP_VERi.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
            Toast.makeText(OTP_VERi.this, "Otp sent Succesfully", Toast.LENGTH_SHORT).show();
        }
    };

    public void enableUserManuallyInputCode() {
        if (otp.getValue().trim().length() == 6) {
            String c = otp.getValue().trim();
            verifyCode(c);
        } else {
            Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show();
        }
    }

    public void verifyCode(String code) {

        credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(OTP_VERi.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(OTP_VERi.this, "Succesfully created", Toast.LENGTH_SHORT).show();

                    addNotes();
                      Intent i=new Intent(OTP_VERi.this,MainActivity.class);
                                    startActivity(i);
                                    finish();

                        }
                        else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(OTP_VERi.this, task.getException().toString(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });
    }
    void addNotes()
    {
        Bundle bundle=getIntent().getExtras();
        String Name=bundle.getString("patient");
        String RelationName=bundle.getString("Relation");
        String Sex=bundle.getString("sex");
        String Age=bundle.getString("age");
        String password=bundle.getString("pass");
        if(id == null) id = mDatabase.push().getKey();

        POJO listdata = new POJO(id,Name,RelationName,phoneNumber,Sex,Age,password);

        mDatabase.child(id).setValue(listdata).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(), "Note Added/Updated", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
