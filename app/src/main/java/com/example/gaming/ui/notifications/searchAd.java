package com.example.gaming.ui.notifications;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gaming.R;

import java.util.ArrayList;

public class searchAd extends RecyclerView.Adapter<searchAd.according>
{
   ArrayList<String> age;
    String intro;
    Context context;
    public searchAd(ArrayList<String> age, String intro,Context context)
    {
        this.age=age;
        this.context=context;
        this.intro=intro;
    }




    @NonNull
    @Override
    public according onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.itean,parent,false);
        return new according(view);
    }

    @Override
    public void onBindViewHolder(@NonNull according holder, int position) {
String a=age.get(position);
holder.ag.setText(a);
    }

    @Override
    public int getItemCount() {
        return age.size();
    }
    public void fil(ArrayList<String> filtered)
    {
        age=filtered;
        notifyDataSetChanged();
    }

    public class according extends RecyclerView.ViewHolder {
TextView ag;
        public according(@NonNull View itemView) {
            super(itemView);
            ag = itemView.findViewById(R.id.ag);
            if (ag.equals("birth")) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final AlertDialog.Builder builder=new AlertDialog.Builder(context);
                        builder.setTitle("Information").setMessage(intro);
                        builder.setCancelable(false);
                        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                dialogInterface.cancel();


                            }
                        });
                        builder.show();

                    }
                });
            }
            else
            {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final AlertDialog.Builder builder=new AlertDialog.Builder(context);

                        builder.setTitle("Information").setMessage(intro);
                        builder.setCancelable(false);
                        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                dialogInterface.cancel();


                            }
                        });
                        builder.show();

                    }
                });
            }
        }
    }
}
