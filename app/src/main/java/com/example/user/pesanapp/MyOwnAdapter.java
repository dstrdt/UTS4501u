package com.example.user.pesanapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 11/3/2017.
 */

public class MyOwnAdapter extends RecyclerView.Adapter<MyOwnAdapter.MAdapter> {

    List<JSONObject> dataGson = new ArrayList<>();
    JSONArray arrayGson;

    public MyOwnAdapter(JSONArray jason){
        arrayGson = jason;
        for (int i=0;i<this.arrayGson.length();i++)
            try {
                dataGson.add(this.arrayGson.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
    }

    @Override
    public MAdapter onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daftar_chat,parent,false);
        return new MAdapter(view);
    }

    @Override
    public void onBindViewHolder(MAdapter holder, int position){
        JSONObject jsonObject = dataGson.get(position);
        try {
            holder.foto.setImageResource(jsonObject.getInt("foto"));
            holder.nama.setText(jsonObject.getString("nama"));
            holder.pesan.setText(jsonObject.getString("pesan"));
            holder.waktu.setText(jsonObject.getString("waktu"));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount(){
        return dataGson.size();
    }

    public class MAdapter extends RecyclerView.ViewHolder{
        ImageView foto;
        TextView nama, pesan, waktu;

        public MAdapter(View itemView) {
            super(itemView);
            foto = (ImageView)itemView.findViewById(R.id.foto);
            nama = (TextView)itemView.findViewById(R.id.pengirim);
            pesan = (TextView)itemView.findViewById(R.id.isi);
            waktu = (TextView)itemView.findViewById(R.id.tanggal);
        }
    }

}
