package com.example.myfirebaseapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {
    private TextView tv_recipe, tv_ingredients, tv_preparation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_layout);
        init();
        getIntentMain();
    }
    private void init(){
        tv_recipe = findViewById(R.id.tv_recipe);
        tv_ingredients = findViewById(R.id.tv_ingredients);
        tv_preparation = findViewById(R.id.tv_preparation);
    }
    private void getIntentMain(){
        Intent i =  getIntent();
        if(i !=null){
            tv_recipe.setText(i.getStringExtra("field_recipe"));
            tv_ingredients.setText(i.getStringExtra("field_ingredients"));
            tv_preparation.setText(i.getStringExtra("field_preparation"));
        }
    }
}
