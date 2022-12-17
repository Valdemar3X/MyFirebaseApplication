package com.example.myfirebaseapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity  extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private  String RECIPE_KEY = "Recipe";
    private DatabaseReference mDataBase;
    private List<Fields> listTemp;
   @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
       super.onCreate(savedInstanceState);
       setContentView(R.layout.read_layout);
       init();
       getDataFromDB();
       onClickItem();
   }
   private void init(){
       listView = findViewById(R.id.lv_read);
       listData = new ArrayList<>();
       listTemp = new ArrayList<>();
       adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
       listView.setAdapter(adapter);
       mDataBase = FirebaseDatabase.getInstance().getReference(RECIPE_KEY);
   }
   private void getDataFromDB() {
       ValueEventListener vListener = new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               if(listData.size()>0)listData.clear();
               if(listTemp.size()>0)listTemp.clear();
               for (DataSnapshot ds : dataSnapshot.getChildren()){
                   Fields field = ds.getValue(Fields.class);
                   assert field !=null;
                   listData.add(field.recipe);
                   listTemp.add(field);
               }
                adapter.notifyDataSetChanged();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       };
      mDataBase.addValueEventListener(vListener);
   }
   private void onClickItem(){
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Fields fields = listTemp.get(i);
               Intent j = new Intent(ReadActivity.this,ShowActivity.class);
               j.putExtra("field_recipe",fields.recipe);
               j.putExtra("field_ingredients",fields.ingredients);
               j.putExtra("field_preparation",fields.preparation);
               startActivity(j);

           }
       });
   }
}
