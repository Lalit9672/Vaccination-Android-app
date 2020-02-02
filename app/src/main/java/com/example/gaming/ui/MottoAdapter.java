package com.example.gaming.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gaming.R;

import java.util.ArrayList;
import java.util.List;

public class MottoAdapter extends RecyclerView.Adapter<MottoAdapter.Photo>
{ List<String> list;
     String intro;
     Context context;
    public MottoAdapter(List<String> list,String intro,Context context)
    {
        this.list=list;
        this.intro=intro;
        this.context=context;
    }



    @NonNull
    @Override
    public Photo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item,parent,false);
        return new Photo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Photo holder, int position) {
   String s=list.get(position);
   holder.vac_name.setText(s);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Photo extends RecyclerView.ViewHolder {
        TextView vac_name;
        public Photo(@NonNull View itemView) {
            super(itemView);
vac_name=itemView.findViewById(R.id.vac_name);
itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view)
    {

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
