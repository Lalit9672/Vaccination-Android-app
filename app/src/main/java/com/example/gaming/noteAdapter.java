package com.example.gaming;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class noteAdapter extends RecyclerView.Adapter<noteAdapter.no>
{
    String date;
    String data;
    public noteAdapter(String date, String data)
    {

        this.date=date;
    this.data=data;
    }
    @NonNull
    @Override
    public no onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.noteiteam,parent,false);
        return new no(view);
    }

    @Override
    public void onBindViewHolder(@NonNull no holder, int position) {
holder.dates.setText(date);
holder.text.setText(data);

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class no extends RecyclerView.ViewHolder {
    TextView dates;
    TextView text;
        public no(@NonNull View itemView) {
        super(itemView);
    dates=itemView.findViewById(R.id.date);
    text=itemView.findViewById(R.id.te);
    }
}


}
