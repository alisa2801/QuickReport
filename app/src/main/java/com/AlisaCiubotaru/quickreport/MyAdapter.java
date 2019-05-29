package com.AlisaCiubotaru.quickreport;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
private List<Problema>listData;

public MyAdapter(List<Problema> listData) {
        this.listData = listData;
        }

@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listdata,parent,false);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Problema ld=listData.get(position);
//        holder.txtid.setText(ld.getId());
        holder.txtname.setText(ld.getUsernameReporter());
        holder.txtmovie.setText(ld.getRoomReporter());

//        final String aux = ld.getMoreDetails();
        //To modify ON_CLICK
        holder.btnmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.txtmovie.setText("Butonul functioneaza");

            }
        });


        }

@Override
public int getItemCount() {
        return listData.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder{
    private TextView txtid,txtname,txtmovie;
    private Button btnmark;
    public ViewHolder(View itemView) {
        super(itemView);
//        txtid=(TextView)itemView.findViewById(R.id.idtxt);
        txtname=(TextView)itemView.findViewById(R.id.nametxt);
        txtmovie=(TextView)itemView.findViewById(R.id.movietxt);
        btnmark = (Button) itemView.findViewById(R.id.btnmarksolved);
    }
}
}

