package com.example.user.pesanapp;

import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChatActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editt;
    EditText masukNama, masukPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        masukNama = (EditText)findViewById(R.id.masukanNama);
        masukPesan = (EditText)findViewById(R.id.masukanPesan);
        preferences = getSharedPreferences(MainActivity.mainPref,0);
        editt = preferences.edit();
    }

    public void kirimPesan(View view) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nama",masukNama.getText().toString());
            jsonObject.put("pesan",masukPesan.getText().toString());
            jsonObject.put("waktu",new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
            jsonObject.put("foto",R.drawable.face);
        }catch (JSONException e){
            e.printStackTrace();
        }

        if (preferences.contains("message")){
            String dataPesan = preferences.getString("message","");
            try {
                JSONArray arrayGson = new JSONArray(dataPesan);
                arrayGson.put(jsonObject);
                editt.putString("message",arrayGson.toString());
                editt.apply();
            }catch (JSONException e){
                e.printStackTrace();
            }
        }else {
            JSONArray gsonArray = new JSONArray();
            gsonArray.put(jsonObject);
            editt.putString("message", gsonArray.toString());
            editt.apply();
        }
        finish();
    }
}
