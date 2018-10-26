package com.example.root.memorableplaces;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    public static ArrayList<String> places;
    ArrayAdapter arrayAdapter;

  //  SharedPreferences sharedPreferences ;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1) {
            //if ( requestCode == RESULT_OK ) {
                String address = data.getStringExtra("address");

                places.add(address);

           try {

                sharedPreferences.edit().putString("places", ObjectSerializer.serialize(places)).apply();

            } catch (IOException e) {
                e.printStackTrace();
            }

            arrayAdapter.notifyDataSetChanged();

            places.get(0);

            if(places.get(1)!= null){
                places.get(1);
            }

            if(places.get(2)!= null )
            places.get(2);
            //}
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listView = findViewById(R.id.listView);


        places = new ArrayList<>();

        places.add("Add a new place...");

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,places);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(getApplication(),MapsActivity.class);
                    startActivityForResult(intent,1);
                }
            }
        });

        try {

            places = (ArrayList<String>)ObjectSerializer.deserialize(sharedPreferences.getString("places",
                    ObjectSerializer.serialize(new ArrayList<>())));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
