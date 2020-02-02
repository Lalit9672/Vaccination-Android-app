package com.example.gaming.ui.main;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gaming.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Withdraw extends Fragment {


    public Withdraw() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_withdraw, container, false);



        return view;
    }

}
