package com.example.gaming;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class Live_frag extends Fragment {
    ViewFlipper viewFlipper;
    public Live_frag()
    {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_live_frag, container, false);
        viewFlipper=view.findViewById(R.id.viewflipper);
        int []image={R.drawable.im,R.drawable.p,R.drawable.born,R.drawable.medicine};
for(int ima:image)
{
flipper(ima);
}
        return view;
    }
    public void flipper(int image)
    {
        ImageView imageView=new ImageView(getContext());
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setInAnimation(getContext(),android.R.anim.slide_in_left);




    }
}
